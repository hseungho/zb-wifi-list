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

    <table id="vertical-1" class="table table-horizontal table-bordered">
        <tr><th>거리(km)</th><td id ="distance"></td></tr>
        <tr><th>관리번호</th><td id ="wifiId"></td></tr>
        <tr><th>자치구</th><td id ="district"></td></tr>
        <tr><th>와이파이명</th><td id ="name"></td></tr>
        <tr><th>도로명주소</th><td id ="address1"></td></tr>
        <tr><th>상세주소</th><td id ="address2"></td></tr>
        <tr><th>설치위치(층)</th><td id ="instlFloor"></td></tr>
        <tr><th>설치유형</th><td id ="instlType"></td></tr>
        <tr><th>설치기관</th><td id ="instlOrg"></td></tr>
        <tr><th>서비스구분</th><td id ="serviceClass"></td></tr>
        <tr><th>망종류</th><td id ="netType"></td></tr>
        <tr><th>설치년도</th><td id ="instlYear"></td></tr>
        <tr><th>실내외구분</th><td id ="inOutType"></td></tr>
        <tr><th>WIFI접속환경</th><td id ="connectEnv"></td></tr>
        <tr><th>X좌표</th><td id ="lat"></td></tr>
        <tr><th>Y좌표</th><td id ="lnt"></td></tr>
        <tr><th>작업일자</th><td id ="workedAt"></td></tr>
    </table>

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
            .catch(() => alert('북마크 그룹 정보를 가져오는데에 실패했습니다'));

        const queryString = window.location.search;
        const wifiFetchUrl = encodeURI(`/wifi${'${queryString}'}`);
        fetch(wifiFetchUrl, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const wifi = data;
                document.querySelector('#distance').innerHTML = `${'${wifi.distance}'}`;
                document.querySelector('#wifiId').innerHTML = `${'${wifi.id}'}`;
                document.querySelector('#district').innerHTML = `${'${wifi.district}'}`;
                document.querySelector('#name').innerHTML = `<a href="wifi-detail.jsp${'${queryString}'}">${'${wifi.name}'}</a>`;
                document.querySelector('#address1').innerHTML = `${'${wifi.address1}'}`;
                document.querySelector('#address2').innerHTML = `${'${wifi.address2}'}`;
                document.querySelector('#instlFloor').innerHTML = `${'${wifi.instlFloor}'}`;
                document.querySelector('#instlType').innerHTML = `${'${wifi.instlType}'}`;
                document.querySelector('#instlOrg').innerHTML = `${'${wifi.instlOrg}'}`;
                document.querySelector('#serviceClass').innerHTML = `${'${wifi.serviceClass}'}`;
                document.querySelector('#netType').innerHTML = `${'${wifi.netType}'}`;
                document.querySelector('#instlYear').innerHTML = `${'${wifi.instlYear}'}`;
                document.querySelector('#inOutType').innerHTML = `${'${wifi.inOutType}'}`;
                document.querySelector('#connectEnv').innerHTML = `${'${wifi.connectEnv}'}`;
                document.querySelector('#lat').innerHTML = `${'${wifi.lat}'}`;
                document.querySelector('#lnt').innerHTML = `${'${wifi.lnt}'}`;
                document.querySelector('#workedAt').innerHTML = `${'${wifi.workedAt}'}`;
            })
            .catch(() => alert('WIFI 정보를 가져오는데에 실패했습니다'));

        function saveWifiBookmark() {
            const bookmarkId = document.querySelector('select').value;
            if (bookmarkId === '') {
                alert('북마크 그룹을 선택해주세요');
                return;
            }

            const params = new URLSearchParams(window.location.search);
            const wifiId = params.get('id');
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
