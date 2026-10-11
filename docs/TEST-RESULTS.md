# Kiểm thử backend V1

- Đạt: Backend chỉ nhận kết nối từ desktop bridge
- Đạt: Tạo Admin, BCrypt và đăng nhập
- Đạt: Thu/chi, chuyển quỹ, phí và số dư đối soát
- Đạt: Chặn số tiền âm/lẻ, ngày trước đầu kỳ, chuyển cùng quỹ, danh mục sai
- Đạt: Sửa/hủy phiếu, chống ghi đè, giữ trước/sau và tính lại số dư
- Đạt: Công nợ phải trả; chặn tạo công nợ khách hàng
- Đạt: Bốn nhóm công nợ, hạn tùy chọn; nhận vay, gốc/lãi riêng, chống trả vượt và hủy trả gốc
- Đạt: Bằng chứng PNG/PDF: kiểm tra nội dung, chống trùng, sửa/gỡ lưu lịch sử, giữ file gốc
- Đạt: Cấp ứng, hoàn ứng và quyết toán không trừ quỹ hai lần; giữ nhất quán khi sửa/xóa
- Đạt: Nhập Excel được chọn, loại bảng phụ và chống nhập trùng
- Đạt: Hai ngân hàng, giữ số 0 đầu; công, tăng ca, sửa/xóa, khóa tháng và lịch sử không đổi số dư
- Đạt: CRUD danh mục và bảo vệ dữ liệu đã được dùng
- Đạt: Vai trò chỉ xem không sửa dữ liệu, đăng ký Admin hoặc sao lưu
- Đạt: Sao lưu bao gồm ảnh/PDF và lịch sử; SQLite toàn vẹn, khôi phục đúng số dư, giữ bản an toàn và thu hồi phiên
- Đạt: Từ chối file khôi phục hỏng và giữ dữ liệu hiện tại
- Đạt: Đổi/quên mật khẩu, xoay mã khôi phục và đăng xuất

Dữ liệu kiểm thử riêng: .test-data-1791716462992. Không dùng dữ liệu vận hành.


## Thu Chi 1.2.4 — Nhập Excel đơn giản (11/10/2026)

- Backend unit tests: 12 templates, required headers, borders, text identifiers, blank input sheet, strict Vietnamese date and integer money rules.
- Isolated API integration: all 12 types committed successfully; previews rolled back without changing business tables or audit. Verified leading zeros, half-day/overtime attendance, requested/issued advances, supplier total payments, carrier payments and loan receipts/principal.
- Invalid dates, formulas, missing amounts, duplicate rows/files, excessive debt payments, insufficient balance and locked attendance months rejected. Changed balance after preview aborts the entire selected commit. Replay and Viewer access rejected.
- Actual Electron UI: 12 colored cards in three columns; template saved through native dialog, fund selected, preview amounts grouped, selected import committed, repeated file blocked, legacy import screen retained; no renderer errors.
- Existing integration suite: 16 groups passed (cash, transfers, obligations, evidence, attendance, old Excel import, backup/restore, authorization).
