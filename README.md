project by VUONGDQ



Yêu cầu:
Database: Mongodb (>= version 3.4)
Ngôn ngữ lập trình là: Java, spring boot, spring mongo data, html, css
Cho 3 collection có mối quan hệ như hình dưới.


Tạo hai tab: tab 1 là Office, tab 2 là user
Trên tab 1:
Hiển thị danh sách các office mà có deletedDate khác null. Trên màn hình danh sách này có 3 cột: Cột 1 hiển thị name, cột 2 hiển thị phone, cột 3 có hai button là button cập nhật office và button xóa office. Khi click vào button xóa office thì hãy hiển thị popup thông báo cho user là bạn chắc chắn muốn xóa office này chứ? Trên popup có hai button là đồng ý và cancel. Khi click vào button đồng ý thì hãy update bản ghi tương ứng với deletedDate là now (thời gian hiện tại).
Trên màn danh sách này có thêm button tạo mới office: Yêu cầu user nhập name và phone. Nếu hai trường hợp user không nhập hai trường này thì hãy hiển thị thông báo cho người dùng là hai trường này  là bắt buộc.
Click vào một office thì ra màn hình chi tiết của office đó. Khi click vào button cập nhật office thì ra màn hình cập nhật name, phone cho office này.
Trên màn hình danh sách office thì có ô textbox để search name hoặc phone office theo từ khóa đã nhập ở ô text đó.


Trên tab 2:
Có button tạo mới user.
Có selectbox danh sách office. Khi user chọn một office thì hãy hiển thị danh sách user của office đó.

Khi user click vào button tạo mới user thì redirect sang màn hình tạo mới. Tại màn hình tạo mới thì hiển thị danh sách office để chọn một office trong selectbox và các ô textbox cho nhập firstName, lastName, address, phone number, career. Hai button radio để chọn gender. Mặc định là để chọn là Male. Và một ô date mà user có thể chọn birthdate theo format lưu trong database là (yyyy/MM/dd).
AccountStatus mặc định khi lưu tạo user là 1.

Tại màn hình danh sách user thì có ô selectbox có giá trị là các office có trong database hoặc giá trị default là tất cả. ô text box cho phép search user theo firstName, lastName, address, phoneNumber, career.
Trong màn hình danh sách user hiển thị các giá trị sau cho từng user:  cột full Name (firstName + lastName), cột address, cột phoneNumber, cột gender, cột chứa hai button là detail và button xóa.
Màn hình danh sách user chỉ hiển thị user có accountStatus là 1.

Click vào button detail thì tới màn hình detail user.
Click vào button xóa thì hiển thị popup là Bạn có muốn user này không? Nếu đồng ý thì cập nhật accountStatus là 2.
Khi click vào một user trong màn hình danh sách thì hãy chuyển sang màn hình detail của user hiển thị các thông tin firstName, lastName, office Name, address, phoneNumber, birthDate(format dạng dd/MM/yyyy), gender, career.

Trên màn hình detail user thì có button update user. Khi click vào button này thì sang màn hình update thông tin user. Các thông tin được update tương tự như màn tạo.
Lưu ý: Ở các màn hình danh sách cần thực hiện phân trang, Hiển thị 10 đối tượng trên một trang






![alt text](https://i.ibb.co/YjZ7QP4/Screenshot-3.pn)
