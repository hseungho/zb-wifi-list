<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/vertical_table.css">
</head>
<body>
    <h1>북마크 삭제</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>
    <div class="wbd-table-container">
        <table id="vertical-1" class="table table-horizontal table-bordered">
            <tr><th>북마크 이름</th><td id ="bookmark_name"></td></tr>
            <tr><th>와이파이명</th><td id ="wifi_name"></td></tr>
            <tr><th>등록일자</th><td id ="created_at"></td></tr>
        </table>
        <div style="display: flex; flex-direction: column; border-collapse: collapse;">
            <div style="margin: auto">
                <a href="javascript:history.go(-1)">돌아가기</a> | <button onclick="deleteWifiBookmark()">삭제</button>
            </div>
        </div>
    </div>
    <div class="footer">
        <jsp:include page="component/footer.jsp"/>
    </div>

    <script>
        const params = new URLSearchParams(window.location.search);
        const wifiBookmarkId = params.get('id');
        const url = encodeURI(`/wifi-bookmark?id=${'${wifiBookmarkId}'}`);
        fetch(url, { method: 'GET' })
            .then(res => res.json())
            .then(data => {
                const wifiBookmark = data;
                if (wifiBookmark === null) {
                    alert("정보를 가져오는데에 실패했습니다.");
                    return;
                }
                document.querySelector('#bookmark_name').innerHTML = `${'${wifiBookmark.bookmarkName}'}`;
                document.querySelector('#wifi_name').innerHTML = `${'${wifiBookmark.wifiName}'}`;
                document.querySelector('#created_at').innerHTML = `${'${wifiBookmark.createdAt}'}`;
            })
            .catch(err => {
                console.log(err)
                alert('정보를 가져오는데에 실패했습니다.')
            });

        function deleteWifiBookmark() {
            if (!confirm('정말 삭제하시겠습니까?')) {
                return;
            }
            fetch(url, { method: 'DELETE' })
                .then(() => window.location.href='wifi-bookmark-list.jsp')
                .catch(err => {
                    console.log(err)
                    alert('북마크를 삭제하는데에 실패하였습니다.')
                });
        }

    </script>
</body>
</html>
