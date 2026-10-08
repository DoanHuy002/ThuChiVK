Thu Chi 1.1.6 bổ sung nhập cước vận chuyển sau khi xuất hàng, tương thích Kho 0.0.19.

- Phiếu xuất đã chọn nhà xe nhưng chưa biết giá cước được đồng bộ vào Chờ nhập cước trong Công nợ phải trả; chưa cộng vào tổng nợ.
- Bổ sung cước tại Thu Chi: tạo công nợ một lần, không trừ nguồn tiền. Nhập 0 khi không phải trả; để trống khi chưa biết giá.
- Đồng bộ lại giữ giá cước đã nhập tại Thu Chi. Kho gửi giá khác sẽ yêu cầu đối chiếu; giữ nguyên các khoản thanh toán.
- Giữ nguyên bốn bảng công nợ, kiểm tra không đủ tiền, dữ liệu và lịch sử cũ.

Đã kiểm tra: cước chưa biết/0đ, chống trùng, bảo vệ giá nhập tại Thu Chi, đối chiếu thay đổi, thao tác nhập cước trên giao diện, thanh toán và sao lưu/khôi phục.
