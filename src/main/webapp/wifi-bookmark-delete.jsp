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
        <table>
            <thead>
                <tr style="height: 30px">
                    <th>북마크 이름</th>
                    <th>와이파이명</th>
                    <th>등록일자</th>
                </tr>
            </thead>
            <tbody>
                <!-- WIFI_BOOKMARK DATA SEGMENT -->
            </tbody>
        </table>
        <div style="display: flex; flex-direction: column; border-collapse: collapse;">
            <div style="margin: auto">
                <a href="javascript:history.go(-1)">돌아가기</a> | <button onclick="deleteWifiBookmark()">삭제</button>
            </div>
        </div>
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
                const tbody = document.querySelector('table tbody');
                tbody.innerHTML = '';
                const tr = document.createElement('tr');
                tr.innerHTML = `
                            <td>${'${wifiBookmark.bookmarkName}'}</td>
                            <td>${'${wifiBookmark.wifiName}'}</td>
                            <td>${'${wifiBookmark.createdAt}'}</td>
                        `;
                tbody.appendChild(tr);
            })
            .catch(err => {
                console.log(err)
                alert(err)
            });

        function deleteWifiBookmark() {
            if (!confirm('정말 삭제하시겠습니까?')) {
                return;
            }
            fetch(url, { method: 'DELETE' })
                .then(() => window.location.href='wifi-bookmark-list.jsp')
                .catch(err => {
                    console.log(err)
                    alert(err)
                });
        }

    </script>
</body>
</html>
