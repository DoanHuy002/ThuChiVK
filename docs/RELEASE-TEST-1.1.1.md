# Kiểm tra bản 1.1.1

Thay đổi: mật khẩu tối thiểu một ký tự thay cho 10; giữ mức tối đa 72 và chặn để trống. Nút mắt hiện/ẩn cho các ô mật khẩu dùng chung, mặc định ẩn khi mở form mới.

`tests/password.cjs` kiểm tra trên Electron nguồn và bản Windows đóng gói với dữ liệu riêng: đăng ký Admin bằng mật khẩu `1`, đăng nhập, đổi thành `2`, đặt lại thành `3` bằng mã khôi phục và đăng nhập lại. Nút mắt giữ đúng giá trị, không gửi form; hai ô trong form đổi mật khẩu hiện/ẩn riêng và trở về ẩn trong form mới. Backend vẫn nhận mật khẩu cũ dài, từ chối mật khẩu rỗng; không có lỗi JavaScript.

Schema vẫn là 3, không thay đổi dữ liệu hay tài khoản có sẵn. Không tạo tài khoản kiểm thử trong thư mục dữ liệu vận hành vừa đặt lại của người dùng.
