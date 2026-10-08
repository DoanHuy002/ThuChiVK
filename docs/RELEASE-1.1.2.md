# Thu Chi Vĩnh Khang 1.1.2

- Nhân viên có chức vụ nhập tự do; danh sách hiển thị chức vụ.
- Tạm ứng lương theo nhân viên và tháng: chi ứng, hoàn lại, khấu trừ vào lương, xem/xóa khấu trừ nhập nhầm. Khấu trừ không trừ nguồn tiền lần nữa; phiếu chi lương ghi số thực trả.
- Ô nhập tiền tự thêm dấu chấm ngăn cách hàng nghìn. Thu xanh lá, chi đỏ.
- Đối tác phải trả chỉ có nhà cung cấp, vận chuyển, bên cho vay và khác; không chọn nhân viên.
- Các mục đã xóa: tìm kiếm, lọc, chọn nhiều, khôi phục và xóa hẳn. Bảo vệ liên kết; xóa bằng chứng cùng chứng từ; giữ nhật ký tối thiểu. Bản sao lưu cũ vẫn giữ dữ liệu cũ.
- Kết nối app Kho cùng máy trong Cài đặt. Đồng bộ phiếu nhập linh kiện đã ghi sổ, thông tin nhà cung cấp, danh sách linh kiện, số lượng và giá. Chống trùng, theo dõi phiếu sửa/hủy; thanh toán bằng phiếu chi liên kết.
- Sao lưu và nâng cấp dữ liệu cũ tự động; giữ nguyên tài khoản và dữ liệu.

## Kết nối Kho

Trong Cài đặt chọn **Kết nối app Kho**. Nếu Kho đã xuất dữ liệu kết nối, chọn `finance-sync.json` trong thư mục dữ liệu Kho. Nếu chưa có, đóng Kho, chọn file mở app Kho rồi chọn thư mục có `warehouse.mv.db`. Thu Chi sao lưu backend Kho và chỉ bổ sung thành phần xuất dữ liệu đọc; không đổi dữ liệu hoặc nghiệp vụ Kho.

Mở lại Kho để dữ liệu kết nối cập nhật mỗi 10 giây. Bấm **Đồng bộ từ kho** trong Thu Chi để ghi công nợ. Đồng bộ không tự tạo phiếu chi. Chỉ phiếu nhập có nhà cung cấp và giá đầy đủ mới tạo nợ; các phiếu nháp/chưa có giá bị bỏ qua và được báo số lượng. Tổng giá trị làm tròn đến đồng, theo tổng số lượng × đơn giá.

Phiếu sửa/hủy/mất ở Kho không tự thay đổi khoản đã ghi nhận ở Thu Chi. Danh sách kết nối báo cần đối chiếu; kiểm tra khoản phải trả và các phiếu thanh toán trước khi chỉnh. Không đồng bộ thanh toán từ Kho vì nguồn dữ liệu này chỉ là phiếu nhập hàng.

Máy chuyển dữ liệu Kho phải giữ file `finance-source.id` cùng thư mục dữ liệu để giữ mã nguồn chống trùng. Nếu Kho được cập nhật và mất thành phần kết nối, dùng nút Kết nối để bổ sung lại. Đây là kết nối cục bộ, không đồng bộ trực tiếp giữa hai máy.

## Xóa hẳn

Xóa thông thường đưa dữ liệu vào Các mục đã xóa. Khôi phục kiểm tra điều kiện ghi sổ; phiếu chỉ được khôi phục khi nguồn tiền, đối tác và khoản nợ liên kết còn hợp lệ. Xóa hẳn yêu cầu Admin, lý do và xác nhận. Có thể chọn nhóm chứng từ đã xóa để xử lý theo thứ tự liên kết, toàn bộ thao tác nằm trong một giao dịch dữ liệu. Nếu một mục bị chặn, cả nhóm không bị thay đổi.

Nội dung chứng từ và bằng chứng bị loại bỏ khỏi dữ liệu hiện tại. Nhật ký tối thiểu giữ người xóa, thời gian, loại và ID mục; các bản sao lưu trước đó vẫn giữ dữ liệu.
