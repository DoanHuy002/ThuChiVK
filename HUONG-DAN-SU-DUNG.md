# Thu Chi Vĩnh Khang V1

## Cài và mở

1. Chạy `releases/VinhKhang-ThuChi-Setup-1.0.2.exe`. Có thể chọn thư mục cài trên D.
2. Mở **Vĩnh Khang Thu Chi** từ shortcut. Bộ cài kèm Java, không cần cài Java/Node riêng trên máy kế toán.
3. Lần đầu nhập họ tên, tên đăng nhập không dấu và mật khẩu 10–72 ký tự để tạo kế toán Admin.
4. Lưu mã khôi phục được hiển thị một lần, rồi đăng nhập.

Dữ liệu mặc định lưu ở `D:\Codex\Projects\VinhKhangThuChiData`, bản sao trong thư mục `backups`. Nếu máy không có ổ D, app báo rõ và không tự ghi dữ liệu sang C. Người triển khai có thể đặt biến `VK_DATA_DIR` đến thư mục được chỉ định trước khi mở app.

## Thiết lập ban đầu

- **Nguồn tiền**: tạo quỹ tiền mặt/tài khoản ngân hàng, số dư và ngày mở theo số đã đối chiếu.
- **Khách hàng / Nhà cung cấp / Nhân viên**: thêm đối tác dùng trong phiếu và công nợ.
- **Danh mục**: tạo nhóm thu, nhóm chi hoặc nhóm cả hai.
- **Người dùng**: Admin đăng ký người dùng tiếp theo, chọn Admin hoặc Chỉ xem. Khóa tài khoản thay vì xóa.

Kế toán lưu phiếu là ghi sổ ngay. Không có bước tự duyệt.

## Ngân hàng và chấm công

**Nguồn tiền → Thêm/Sửa → Loại Ngân hàng**: ghi tên ngân hàng, số tài khoản và chủ tài khoản. Tài khoản cũ sau nâng cấp để trống các thông tin này, có thể bổ sung bằng Sửa. Số 0 đầu tài khoản được giữ. Hai tài khoản tạo thành hai nguồn tiền độc lập; phiếu chi trả lương chọn đúng nguồn tiền. App ghi sổ, không tự chuyển tiền ngân hàng.

**Nhân viên → Thêm/Sửa**: nhập mã chấm công riêng cho từng người; có thể để trống nếu chưa dùng máy. Mã không trùng và giữ số 0 đầu. Không đổi loại/xóa nhân viên đã có lịch sử công.

**Chấm công**: chọn tháng, tìm nhân viên, bấm ô ngày. Chọn Đi làm, Nửa ngày, Nghỉ phép, Nghỉ không lương hoặc Nghỉ; ghi tăng ca theo bước 0,25 giờ và ghi chú. Ô trống nghĩa là chưa chấm, không tự tính là nghỉ. Công đi làm và ngày phép được tổng hợp riêng. Sửa/xóa/chấm lại công đã xóa cần lý do.

**Chốt và khóa tháng** chặn thêm/sửa/xóa công. Admin **Mở khóa tháng** kèm lý do nếu cần điều chỉnh. Xuất bảng công CSV mở bằng Excel. Công chưa làm giảm nguồn tiền và chưa tự tính lương; kế toán ghi phiếu chi lương theo số tiền đã xác nhận.

Database nâng cấp tự động từ schema 1 sang 2, có sao lưu trước nâng cấp. Bản 1.0.2 khôi phục được backup của 1.0.1; không dùng app cũ mở database mới. Dữ liệu máy chấm công là phần chuẩn bị, chưa có kết nối thiết bị trong phiên bản này.

## Làm việc hằng ngày

**Phiếu thu / Phiếu chi**: chọn ngày, nguồn, số tiền, nội dung, nhóm và đối tác. Nếu thanh toán công nợ phải chọn khoản nợ liên kết và đúng đối tác. Có tìm kiếm, lọc ngày, nguồn và trạng thái, xuất CSV.

**Chuyển quỹ**: chọn nguồn chuyển và nguồn nhận khác nhau, tiền chuyển và phí nếu có. Không ghi thêm một phiếu chi và một phiếu thu cho cùng lần chuyển.

**Công nợ**: tạo khoản gốc từ chứng từ đã xác nhận, chọn Thanh toán để ghi phiếu liên kết. Có thanh toán một phần, kiểm tra dư và hạn. Không tự suy ra nợ từ dòng Excel.

**Tạm ứng**: tạo khoản đề nghị → Cấp ứng → Hoàn ứng và/hoặc Quyết toán. Quyết toán cần nội dung/chứng từ, không trừ quỹ lần nữa. Tiền nhân viên tự bỏ ra chi hộ công ty nên tạo khoản phải trả nhân viên rồi thanh toán hoàn trả.

**Sửa/xóa phiếu**: ghi lý do. Xóa là hủy hiệu lực, vẫn xem được trong trạng thái Đã hủy và Nhật ký. Nhật ký có trước/sau và người thực hiện. Thời gian nhật ký hiển thị UTC (Việt Nam cộng 7 giờ).

## Nhập Excel trên máy

Mở **Nhập Excel → Chọn file Excel**, chọn sổ .xlsx đã lưu trên máy. Lọc sheet, chọn dòng, xác nhận ngày/nguồn/chiều trước khi ghi sổ. App chỉ đọc bảng công ty có ngày, diễn giải, thu, chi và tồn; không tự nhập bảng phụ hoặc sheet tiền cá nhân. Chặn dòng có cả thu và chi; dòng thiếu ngày phải bổ sung. Lưu workbook trong Excel trước để cập nhật kết quả công thức. Đối soát số dư đầu kỳ và cuối kỳ trước khi nhập nhiều tháng.

Khóa chống nhập trùng theo tên sheet, hàng và cột. Không đổi tên sheet hoặc vị trí dòng của sổ đang nhập. Sau khôi phục, chọn lại workbook để tiếp tục đối soát.

## Chuyển sang máy khác

Máy cũ: **Cài đặt → Tạo sao lưu → Lưu file**. Máy mới: tải bộ cài từ GitHub Releases, cài app, tạo Admin tạm → **Cài đặt → Khôi phục từ file**, nhập mật khẩu Admin hiện tại và chọn backup. Sau đó đăng nhập bằng tài khoản trong backup.

Mỗi máy có dữ liệu riêng. Cập nhật qua Internet chỉ cập nhật phần mềm; không đồng bộ dữ liệu kế toán giữa các máy.

## Cập nhật qua Internet

Kho phát hành đã gắn sẵn: https://github.com/DoanHuy002/ThuChiVK . Không cần tài khoản GitHub trên máy kế toán. App kiểm tra khi mở và mỗi 4 giờ. Khi có bản mới, đăng nhập Admin, vào **Cài đặt → Tải bản mới → Sao lưu và cài đặt**. App kiểm tra file tải và sao lưu trước khi cài. Không có Internet vẫn dùng app bình thường; thử lại khi mạng hoạt động.

## Mật khẩu

Quên mật khẩu: dùng mã khôi phục đã lưu. Sau đặt lại/đổi mật khẩu, lưu mã khôi phục mới. Admin tạo và khóa người dùng tại mục Người dùng.

## Giới hạn V1

Chưa có đồng bộ nhiều máy, chữ ký số bộ cài hoặc file hóa đơn đính kèm. Quyết toán tạm ứng đã ghi không sửa/xóa ở V1. Bộ cài và mã nguồn công khai không chứa số liệu doanh nghiệp.
