<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/table.css">
    <style>
        table{
            display:flex;
            display: -webkit-box;
            display: -ms-flexbox;
            overflow-x: auto;
            overflow-y: hidden;
        }
        tbody { display:flex }
        th,td {
            height: 40px;
            display: flex; /* 행을 가운데 정렬하기 위해 flex 사용 */
            justify-content: center; /* 수평 가운데 정렬 */
            align-items: center; /* 수직 가운데 정렬 */
        }
    </style>
</head>
<script>
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    const url = encodeURI(`/wifi?id=${'${id}'}`);
    fetch(url, { method: 'GET' })
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
</script>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="/">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <br><br>
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
</body>
</html>
