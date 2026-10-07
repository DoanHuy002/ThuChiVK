# Kiểm thử 1.0.2

- Backend: 14 nhóm, bao gồm ngân hàng riêng biệt, số 0 đầu, mã chấm công không trùng, tăng ca, sửa/hủy/chấm lại, chống ghi đè, khóa/mở khóa tháng, quyền chỉ xem và audit. Chấm công không thay đổi số dư.
- Migration từ backend 1.0.1 thật: giữ tài khoản, số dư, ID phiếu, version và toàn bộ audit; có backup trước migration.
- Khôi phục backup schema 1 vào app schema 2: nâng cấp file tạm, thu hồi phiên; từ chối schema sai hoặc mới hơn mà giữ dữ liệu hiện tại.
- Kiểm thử cập nhật: quyền, mất mạng, checksum lỗi, sao lưu lỗi và thứ tự đóng dữ liệu trước cài.

Chỉ sử dụng dữ liệu giả trong thư mục kiểm thử riêng. Không đưa database hoặc dữ liệu doanh nghiệp lên GitHub.

- Giao diện Electron từ mã nguồn và executable đóng gói đã đạt: nhập ngân hàng/số tài khoản, chọn tài khoản khi chuyển quỹ, mã nhân viên, ghi/sửa/xóa/chấm lại công, khóa/mở khóa tháng và xuất CSV.
- Đã công bố GitHub Release v1.0.2. Dùng electron-updater thật với phiên bản client 1.0.1: nhận v1.0.2 không dùng thông tin đăng nhập GitHub, tải EXE và kiểm tra SHA512 thành công. Kiểm thử trực tuyến dừng sau tải, không thay app vận hành.
