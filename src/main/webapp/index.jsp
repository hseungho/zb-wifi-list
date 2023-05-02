<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/table.css">
  </head>
  <body>
    <h1>와이파이 정보 구하기</h1>
    <a href="/">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a href="/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
    <br><br>
    <form method="GET" id="wifi-form">
      <label for="LAT">LAT:</label> <input type="text" id="LAT" name="LAT" value="0.0">
      <label for="LNT">LNT:</label> <input type="text" id="LNT" name="LNT" value="0.0">
      <button type="button" onclick="getLocation()">현재 위치 가져오기</button>
      <button type="button" onclick="getNearWifiList()">근처 WIFI 정보 보기</button>
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

  </body>

  <script>
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

    function getNearWifiList() {
      const lat = document.getElementById("LAT").value;
      const lnt = document.getElementById("LNT").value;
      if (lat === '' || lnt === '' || lat === '0.0' || lnt === '0.0') {
        alert("현재 위치를 가져와주세요.");
        return;
      }

      const params = { lat, lnt };
      const query = Object.keys(params)
              .map(k => encodeURIComponent(k) + "=" + encodeURIComponent(params[k]))
              .join("&");
      const url = `/wifi/near?`+query;
      fetch(url)
              .then(response => response.json())
              .then(data => {
                const wifiList = data;
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                if (wifiList.length > 0) {
                  wifiList.forEach(wifi => {
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
                  });
                }
              })
              .catch(error => {
                console.error('Error:', error);
                alert('WIFI 정보를 가져오는 중 오류가 발생했습니다.');
              });

    }
  </script>
</html>
