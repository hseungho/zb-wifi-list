<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/horizontal_table.css">
    <link rel="stylesheet" href="css/pagination.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div class="header">
    <jsp:include page="component/header.jsp"/>
</div>
<form method="GET" id="wifi-form">
    <label for="LAT">LAT:</label> <input type="text" id="LAT" name="LAT" value="0.0">
    <label for="LNT">LNT:</label> <input type="text" id="LNT" name="LNT" value="0.0">
    <button type="button" onclick="getLocation()">현재 위치 가져오기</button>
    <button type="button" onclick="getNearWifiList(1)">근처 WIFI 정보 보기</button>
</form>
<table>
    <thead>
    <tr style="height: 30px">
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="17" style="text-align: center">위치 정보를 입력한 후 조회해 주세요.</td>
    </tr>
    </tbody>
</table>
<div id="pagination-container" style="display: flex; flex-direction: column; border-collapse: collapse; margin-top: 30px">
    <div id="pagination" class="pagination"></div>
</div>
<div class="footer">
    <jsp:include page="component/footer.jsp"/>
</div>

<script>
    const params = new URLSearchParams(window.location.search);
    const lat = params.get('lat');
    const lnt = params.get("lnt");
    if (lat !== null && lnt !== null) {
        const page = params.get('page');
        document.getElementById("LAT").value = lat;
        document.getElementById("LNT").value = lnt;
        getNearWifiList(page);
    }

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("이 브라우저로는 현재 위치 정보를 가져올 수 없습니다.");
        }
    }

    function showPosition(position) {
        document.getElementById("LAT").value = position.coords.latitude;
        document.getElementById("LNT").value = position.coords.longitude;
    }

    let totalItems = 0;
    let totalPages = 0;
    let startPage = 0;
    let endPage = 0;
    const itemsPerPage = 20;
    const paginationPerPage = 10;

    function getNearWifiList(page) {
        const lat = document.getElementById("LAT").value;
        const lnt = document.getElementById("LNT").value;
        if (lat === '' || lnt === '' || lat === '0.0' || lnt === '0.0') {
            alert("현재 위치를 가져와주세요.");
            return;
        }

        const params = {lat, lnt, page};
        const query = Object.keys(params)
            .map(k => encodeURIComponent(k) + "=" + encodeURIComponent(params[k]))
            .join("&");
        const url = `/wifi/near?` + query;
        fetch(url, {method: 'GET'})
            .then(response => response.json())
            .then(data => {
                if (data === null) {
                    if (confirm('WIFI 데이터가 없습니다. WIFI를 저장하시겠습니까?')) {
                        window.location.href = 'load-wifi.jsp';
                        return;
                    } else {
                        return;
                    }
                }

                if (totalItems === 0) {
                    totalItems = data.totalItems;
                    totalPages = Math.ceil(totalItems / itemsPerPage);
                }

                startPage = Math.floor((page - 1) / paginationPerPage) * paginationPerPage + 1;
                endPage = Math.min(startPage + paginationPerPage - 1, totalPages);

                const wifiList = data.wifiList;
                renderPage(wifiList);
                renderPagination();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('WIFI 정보를 가져오는 중 오류가 발생했습니다.');
            });

        function renderPage(wifiList) {
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = '';
            if (wifiList !== null && wifiList.length > 0) {
                wifiList.forEach(wifi => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                <td>${'${wifi.distance}'}</td>
                <td>${'${wifi.id}'}</td>
                <td>${'${wifi.district}'}</td>
                <td><a href="wifi-detail.jsp?id=${'${wifi.id}'}&lat=${'${lat}'}&lnt=${'${lnt}'}">${'${wifi.name}'}</a></td>
                <td>${'${wifi.address1}'}</td>
                <td>${'${wifi.address2}'}</td>
                <td>${'${wifi.instlFloor}'}</td>
                <td>${'${wifi.instlType}'}</td>
                <td>${'${wifi.instlOrg}'}</td>
                <td>${'${wifi.serviceClass}'}</td>
                <td>${'${wifi.netType}'}</td>
                <td>${'${wifi.instlYear}'}</td>
                <td>${'${wifi.inOutType}'}</td>
                <td>${'${wifi.connectEnv}'}</td>
                <td>${'${wifi.lat}'}</td>
                <td>${'${wifi.lnt}'}</td>
                <td>${'${wifi.workedAt}'}</td>
              `;
                    tbody.appendChild(tr);
                });
            } else {
                alert('WIFI 정보를 가져오는데에 실패하였습니다.')
            }
        }

        function renderPagination() {
            let pagination = document.getElementById("pagination");
            pagination.innerHTML = "";

            const firstLink = document.createElement("a");
            firstLink.href = "javascript:void(0);";
            firstLink.textContent = "처음";
            firstLink.addEventListener("click", function () {
                page = 1;
                updatePagination(page);
            });
            pagination.appendChild(firstLink);

            if (startPage > 1) {
                const prevLink = document.createElement("a");
                prevLink.href = "javascript:void(0);";
                prevLink.textContent = "이전";
                prevLink.addEventListener("click", function () {
                    page = startPage - 10;
                    updatePagination(page);
                });
                pagination.appendChild(prevLink);
            }

            // 페이징 버튼 생성
            for (let i = startPage; i <= endPage; i++) {
                const link = document.createElement("a");
                link.href = "javascript:void(0);";
                link.textContent = i;
                if (page === i) {
                    link.classList.add("active");
                }
                link.addEventListener("click", function () {
                    page = parseInt(this.textContent);
                    getNearWifiList(page);
                    updatePagination(page);
                });
                pagination.appendChild(link);
            }

            if (endPage < totalPages) {
                const nextLink = document.createElement("a");
                nextLink.href = "javascript:void(0);";
                nextLink.textContent = "다음";
                nextLink.addEventListener("click", function () {
                    page = endPage + 1;
                    updatePagination(page);
                });
                pagination.appendChild(nextLink);
            }

            const lastLink = document.createElement("a");
            lastLink.href = "javascript:void(0);";
            lastLink.textContent = "마지막";
            lastLink.addEventListener("click", function () {
                page = totalPages;
                updatePagination(page);
            });
            pagination.appendChild(lastLink);

        }

        function updatePagination(page) {
            startPage = Math.floor((page - 1) / paginationPerPage) * paginationPerPage + 1;
            endPage = Math.min(startPage + paginationPerPage - 1, totalPages);

            getNearWifiList(page);
            renderPagination();
        }
    }
</script>
</body>
</html>
