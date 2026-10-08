# Kiểm tra bản 1.1.0 — 08/10/2026

- Backend: 16 nhóm kiểm tra đạt, gồm thu/chi/chuyển quỹ/phí, sửa/hủy/phiên bản, bốn nhóm nợ và hạn tùy chọn, gốc/lãi/nhận tiền vay, tạm ứng, nhập Excel, chấm công và quyền người dùng.
- Bằng chứng: PNG/PDF, kiểm tra loại file, chống trùng, sửa chú thích/gỡ giữ bản gốc và audit, chống ghi đè, người chỉ xem không sửa.
- Backup/restore: ảnh/PDF còn nguyên byte sau khôi phục, giữ lịch sử gỡ, số dư/tài khoản/chấm công đúng; thu hồi phiên và giữ bản trước khôi phục.
- Migration: chạy JAR cũ 1.0.1 (schema 1) và 1.0.2 (schema 2) tạo dữ liệu thử, mở bằng JAR 1.1.0 (schema 3), giữ ID/số tiền/số dư/nhật ký/chấm công. Khoản phải thu cũ được giữ trong chứng từ và không xuất hiện trong danh sách hiện tại. Khôi phục backup cũ được nâng cấp trong file tạm; chặn khai báo schema sai/mới hơn.
- Electron nguồn và Windows đóng gói: tạo Admin, đăng nhập/đăng xuất, ngân hàng/số 0 đầu, thu/chi/chuyển quỹ, công nợ, chấm công/sửa/gỡ/khóa tháng, Excel, tìm kiếm, audit, điều hướng. Tạo phiếu kèm ảnh, xem/phóng to, sửa chú thích và gỡ giữ lịch sử. Biểu đồ tròn/cột, lịch tiếng Việt và bấm chú giải để lọc đều đạt; không có lỗi JavaScript.
- Cập nhật: kiểm tra quyền, URL GitHub, mất mạng, file tải hỏng, sao lưu lỗi, không hạ phiên bản và sao lưu/đóng dữ liệu trước cài đặt đạt bằng tests/updates.cjs.
- Mã chuẩn bị phát hành và JAR được rà soát: không có workbook/dòng dữ liệu Excel thật; import-preview đóng gói là danh sách rỗng. Bộ cài không chứa database hoạt động, mật khẩu/mã khôi phục hoặc dữ liệu thử.

Các phiên kiểm tra dùng thư mục dữ liệu riêng. Bản sao và ảnh/PDF nằm trong SQLite; giới hạn bằng chứng được nêu trong hướng dẫn sử dụng. Bộ cài 1.1.0 được build vào `releases/v1.1.0` để giữ tách khỏi bản chạy cũ.
