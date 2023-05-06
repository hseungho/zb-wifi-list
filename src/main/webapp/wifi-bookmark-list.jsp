<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="css/horizontal_table.css">
</head>
<body>
    <h1>북마크 목록</h1>
    <div class="header">
        <jsp:include page="component/header.jsp"/>
    </div>
    <div class="wbl-table-container">
        <table>
            <thead>
            <tr style="height: 30px">
                <th>ID</th>
                <th>북마크 이름</th>
                <th>와이파이명</th>
                <th>등록일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
                <!-- WIFI_BOOKMARK DATA SEGMENT -->
            </tbody>
        </table>
    </div>
    <div class="footer">
        <jsp:include page="component/footer.jsp"/>
    </div>

    <script>
        const listFetchUrl = encodeURI('/wifi-bookmark/list');
        fetch(listFetchUrl, { method : 'GET' })
            .then(res => res.json())
            .then(data => {
                const wifiBookmarkList = data;
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                if (wifiBookmarkList !== null && wifiBookmarkList.length > 0) {
                    wifiBookmarkList.forEach(wifiBookmark => {
                        const tr = document.createElement('tr');
                        tr.innerHTML = `
                            <td>${'${wifiBookmark.id}'}</td>
                            <td>${'${wifiBookmark.bookmarkName}'}</td>
                            <td><a href="wifi-detail.jsp?id=${'${wifiBookmark.wifiId}'}">${'${wifiBookmark.wifiName}'}</a></td>
                            <td>${'${wifiBookmark.createdAt}'}</td>
                            <td><center>
                                <a href="/wifi-bookmark-delete.jsp?id=${'${wifiBookmark.id}'}">삭제</a>
                            </center></td>
                        `;
                        tbody.appendChild(tr);
                    });
                } else {
                    const tr = document.createElement('tr');
                    tr.innerHTML = "<td colspan='5' style='text-align: center;'>아직 북마크가 없습니다.</td>";
                    tbody.appendChild(tr);
                }
            })
            .catch(err => {
                console.log(err);
                alert('북마크를 조회하는데에 실패했습니다.');
            })
    </script>

</body>
</html>
