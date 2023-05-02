<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/vertical_table.css">
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>

    <select>
        <option value="">북마크 그룹 이름 선택</option>
    </select>
    <button onclick="saveWifiBookmark()">북마크 추가하기</button>

    <div class="table-container">
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
                </tr>
            </tbody>
        </table>
    </div>

    <script>
        const bookmarkListFetchUrl = encodeURI('/bookmark/list');
        fetch(bookmarkListFetchUrl, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const bookmarkList = data;
                const select = document.querySelector('select');
                bookmarkList.forEach(bookmark => {
                    const option = document.createElement('option');
                    option.setAttribute('value', `${'${bookmark.id}'}`);
                    option.text = `${'${bookmark.name}'}`;
                    select.append(option);
                });
            })
            .catch(err => alert(err));

        const params = new URLSearchParams(window.location.search);
        const wifiId = params.get('id');
        const wifiFetchUrl = encodeURI(`/wifi?id=${'${wifiId}'}`);
        fetch(wifiFetchUrl, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const wifi = data;
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                const tr = document.createElement('tr');
                tr.innerHTML = `
                                  <td>${'${wifi.distance}'}</td>
                                  <td>${'${wifi.id}'}</td>
                                  <td>${'${wifi.district}'}</td>
                                  <td><a href="wifi-detail.jsp?id=${'${wifi.id}'}">${'${wifi.name}'}</a></td>
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
            })
            .catch(err => console.log(err));

        function saveWifiBookmark() {
            const bookmarkId = document.querySelector('select').value;
            if (bookmarkId === '') {
                alert('북마크 그룹을 선택해주세요');
                return;
            }

            const url = encodeURI('/wifi-bookmark');
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify({
                    wifiId: wifiId,
                    bookmarkId: bookmarkId
                })
            })
                .then(() => {
                    if (confirm('북마크가 저장되었습니다.\n북마크로 이동하시겠습니까?')) {
                        window.location.href = '/wifi-bookmark-list.jsp';
                    }
                })
                .catch(err => {
                    console.log(err);
                    alert(err);
                })
        }

    </script>
</body>
</html>
