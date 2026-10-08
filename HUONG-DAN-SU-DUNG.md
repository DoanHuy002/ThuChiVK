# Thu Chi Vĩnh Khang V1

## Cài và mở

1. Chạy `releases/VinhKhang-ThuChi-Setup-1.1.0.exe`. Có thể chọn thư mục cài trên D.
2. Mở **Vĩnh Khang Thu Chi** từ shortcut. Bộ cài kèm Java, không cần cài Java/Node riêng trên máy kế toán.
3. Lần đầu nhập họ tên, tên đăng nhập không dấu và mật khẩu 10–72 ký tự để tạo kế toán Admin.
4. Lưu mã khôi phục được hiển thị một lần, rồi đăng nhập.

Dữ liệu mặc định lưu ở `D:\Codex\Projects\VinhKhangThuChiData`, bản sao trong thư mục `backups`. Nếu máy không có ổ D, app báo rõ và không tự ghi dữ liệu sang C. Người triển khai có thể đặt biến `VK_DATA_DIR` đến thư mục được chỉ định trước khi mở app.

## Thiết lập ban đầu

- **Nguồn tiền**: tạo quỹ tiền mặt/tài khoản ngân hàng, số dư và ngày mở theo số đã đối chiếu.
- **Khách hàng / Đại lý, Bên phải trả, Người góp vốn, Nhân viên**: thêm đối tác dùng trong phiếu và công nợ.
- **Danh mục**: tạo nhóm thu, nhóm chi hoặc nhóm cả hai.
- **Người dùng**: Admin đăng ký người dùng tiếp theo, chọn Admin hoặc Chỉ xem. Khóa tài khoản thay vì xóa.

Kế toán lưu phiếu là ghi sổ ngay. Không có bước tự duyệt.

## Ngân hàng và chấm công

**Nguồn tiền → Thêm/Sửa → Loại Ngân hàng**: ghi tên ngân hàng, số tài khoản và chủ tài khoản. Tài khoản cũ sau nâng cấp để trống các thông tin này, có thể bổ sung bằng Sửa. Số 0 đầu tài khoản được giữ. Hai tài khoản tạo thành hai nguồn tiền độc lập; phiếu chi trả lương chọn đúng nguồn tiền. App ghi sổ, không tự chuyển tiền ngân hàng.

**Nhân viên → Thêm/Sửa**: nhập mã chấm công riêng cho từng người; có thể để trống nếu chưa dùng máy. Mã không trùng và giữ số 0 đầu. Không đổi loại/xóa nhân viên đã có lịch sử công.

**Chấm công**: chọn tháng, tìm nhân viên, bấm ô ngày. Chọn Đi làm, Nửa ngày, Nghỉ phép, Nghỉ không lương hoặc Nghỉ; ghi tăng ca theo bước 0,25 giờ và ghi chú. Ô trống nghĩa là chưa chấm, không tự tính là nghỉ. Công đi làm và ngày phép được tổng hợp riêng. Sửa/xóa/chấm lại công đã xóa cần lý do.

**Chốt và khóa tháng** chặn thêm/sửa/xóa công. Admin **Mở khóa tháng** kèm lý do nếu cần điều chỉnh. Xuất bảng công CSV mở bằng Excel. Công chưa làm giảm nguồn tiền và chưa tự tính lương; kế toán ghi phiếu chi lương theo số tiền đã xác nhận.

Database nâng cấp tự động từ schema 1 hoặc 2 lên 3, có sao lưu trước nâng cấp. Bản 1.1.0 khôi phục được backup của 1.0.1 và 1.0.2; không dùng app cũ mở database mới. Dữ liệu máy chấm công là phần chuẩn bị, chưa có kết nối thiết bị trong phiên bản này.

## Làm việc hằng ngày

**Phiếu thu / Phiếu chi**: chọn ngày, nguồn, số tiền, nội dung, nhóm và đối tác. Nếu thanh toán công nợ phải chọn khoản nợ liên kết và đúng đối tác. Có tìm kiếm, lọc ngày, nguồn và trạng thái, xuất CSV.

**Chuyển quỹ**: chọn nguồn chuyển và nguồn nhận khác nhau, tiền chuyển và phí nếu có. Không ghi thêm một phiếu chi và một phiếu thu cho cùng lần chuyển.

**Công nợ**: tạo khoản gốc từ chứng từ đã xác nhận, chọn Thanh toán để ghi phiếu liên kết. Có thanh toán một phần, kiểm tra dư và hạn. Không tự suy ra nợ từ dòng Excel.

**Tạm ứng**: tạo khoản đề nghị → Cấp ứng → Hoàn ứng và/hoặc Quyết toán. Quyết toán cần nội dung/chứng từ, không trừ quỹ lần nữa. Tiền nhân viên tự bỏ ra chi hộ công ty nên tạo khoản phải trả nhân viên rồi thanh toán hoàn trả.

**Sửa/xóa phiếu**: ghi lý do. Xóa là hủy hiệu lực, vẫn xem được trong trạng thái Đã hủy và Nhật ký. Nhật ký có trước/sau và người thực hiện. Thời gian nhật ký hiển thị theo giờ Việt Nam, ngày/tháng/năm.

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

Chưa có đồng bộ nhiều máy, chữ ký số bộ cài. Quyết toán tạm ứng đã ghi không sửa/xóa ở V1. Bộ cài và mã nguồn công khai không chứa số liệu doanh nghiệp.

## Thu tiền, đối tác và công nợ trong bản 1.1.0

**Khách hàng / Đại lý** lưu nhóm Đại lý/Bán lẻ, tên, điện thoại, địa chỉ và ghi chú. Bấm **Lịch sử thu** để xem phiếu của người đó trong kỳ đang chọn. Khách lẻ có thể thu không gắn hồ sơ. Người góp vốn có danh sách riêng. App không quản lý công nợ khách hàng; dữ liệu phải thu từ bản cũ được giữ nguyên trong database và nhật ký, không xuất hiện trong danh sách công nợ mới.

**Phiếu thu → Nguồn thu**: Đại lý, Bán lẻ, Góp vốn, Nhận tiền vay, Thu khác. Chọn đúng nguồn tiền nhận: tài khoản công ty hoặc tài khoản phụ đều là tiền công ty, mỗi tài khoản có số dư riêng. Chọn đối tác đại lý/bán lẻ/người góp vốn sẽ gợi ý nguồn thu, kế toán kiểm tra trước khi lưu. Thu không gắn công nợ không tạo khoản phải thu.

**Bên phải trả** lưu nhà cung cấp, bên vận chuyển, bên cho vay hoặc đối tác khác. **Công nợ phải trả** chọn nhóm Nhà cung cấp, Bên vận chuyển, Vay vốn, Công nợ khác; hạn thanh toán có thể bỏ trống. Tạo khoản nợ chưa làm thay đổi số dư tiền. Bấm **Thanh toán** ghi phiếu chi một phần hoặc toàn bộ, chọn nguồn tiền; phiếu chi giảm tiền và nợ. Sửa khoản nợ cần lý do. Bấm **Xem chứng từ** để xem bằng chứng và các phiếu liên kết.

**Vay vốn**: số tiền công nợ là tổng gốc vay đã xác nhận, không phải hạn mức dự kiến. **Nhận tiền vay** tạo phiếu thu, tăng nguồn tiền và trình bày riêng trên tổng quan; không cộng gốc vay thêm lần nữa. **Trả gốc** tạo phiếu chi và giảm nợ gốc. **Trả lãi** tạo phiếu chi, giữ nguyên nợ gốc. Có thể trả lãi sau khi gốc đã trả hết. Khi hủy phiếu trả gốc, số nợ được tính lại; gốc/lãi có lịch sử riêng. Khoản có phiếu vay không đổi sang nhóm khác.

## Bằng chứng, ngày tháng và tổng quan

Khi tạo/sửa phiếu thu, chi, chuyển quỹ hoặc khoản nợ, bấm **Chèn ảnh / PDF bằng chứng**, chọn nhiều file và ghi chú. Phiếu được ghi sổ một lần; nếu tải file lỗi, màn hình chứng từ giữ file chờ để tải lại mà không ghi tiền lần nữa. Có thể mở **Xem** trên dòng phiếu để bổ sung, sửa chú thích hoặc gỡ bằng chứng. Gỡ cần lý do và vẫn giữ bản gốc để tra cứu lịch sử. Người chỉ xem được mở và lưu bằng chứng, không sửa/gỡ.

Hỗ trợ PNG, JPEG và PDF: tối đa 8 MB/file, 20 file đang sử dụng/chứng từ, tổng 256 MB bằng chứng gồm cả bản đã gỡ. Chọn ảnh thu nhỏ để tiết kiệm dung lượng. Ảnh mở được phóng to; PDF có nút lưu file để mở bằng phần mềm trên máy. Ảnh/PDF nằm trong bản sao SQLite, sao lưu và khôi phục/chuyển máy bao gồm cả bằng chứng và lịch sử. Giới hạn file khôi phục là 512 MB.

Ngày nhập và lịch chọn ngày hiển thị **dd/mm/yyyy**, tháng và các nút lịch bằng tiếng Việt, độc lập ngôn ngữ Windows. Ngày/tháng chuẩn ISO vẫn được dùng bên trong database. Nhật ký đổi sang giờ Việt Nam.

**Tổng quan** có kỳ nhanh Hôm nay/Tháng này/Tháng trước/Năm nay, tiền theo từng tài khoản, tổng thu/chi/dòng tiền thuần; công nợ hiện tại, quá hạn, đến hạn 7 ngày và tạm ứng. Biểu đồ cột theo ngày cho kỳ tối đa 45 ngày, theo tháng cho kỳ dài hơn; biểu đồ tròn chia nguồn thu và mục chi, gốc/lãi vay riêng. Bấm cột, chú giải hoặc số liệu để chuyển tới danh sách chi tiết. Có bảng khoản chi lớn và giao dịch gần đây. Khoản không ghi hạn vẫn nằm trong tổng nợ, không tự gắn là quá hạn. Thu/chi chưa phải lợi nhuận; chuyển giữa hai tài khoản nội bộ chỉ tính phí vào chi.
