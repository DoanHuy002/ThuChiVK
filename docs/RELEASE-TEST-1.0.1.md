# Kiểm thử phát hành 1.0.1

- Đạt 13 nhóm kiểm thử backend với workbook giả và đối chiếu bộ đọc với workbook gốc trong dữ liệu kiểm thử riêng.
- Đạt giao diện Electron từ mã nguồn và executable Windows đóng gói.
- Đạt quyền cập nhật, URL GitHub, lưu cấu hình, không hạ phiên bản, mất mạng, lỗi checksum, lỗi sao lưu và đóng dữ liệu trước chạy installer.
- Đã công bố GitHub Release v1.0.1 với EXE, blockmap và latest.yml.
- Đã kiểm tra qua electron-updater thật: giả lập phiên bản cũ 1.0.0, nhận v1.0.1 qua GitHub công khai không có thông tin đăng nhập, tải EXE thành công và xác minh SHA512.
- Kiểm thử cập nhật trực tuyến dừng ở tải/kiểm tra file; không chạy installer thay thế app vận hành. Thứ tự sao lưu/đóng backend trước installer được kiểm tra bằng kiểm thử cơ chế cập nhật.
- Đã kiểm tra nguồn Git và resource backend đóng gói: không có database, workbook, mô tả dòng thu chi thật hoặc phân tích tài chính doanh nghiệp.

Dữ liệu và bộ cài vận hành được lưu độc lập. Cập nhật phần mềm không đồng bộ dữ liệu giữa máy.
