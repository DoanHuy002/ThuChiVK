# Thu Chi Vĩnh Khang

App Windows quản lý thu chi, công nợ và tạm ứng: Electron + React, Spring Boot, SQLite cục bộ. Kế toán là Admin và ghi sổ trực tiếp. Sửa/hủy giao dịch giữ lịch sử trước/sau.

## Cài đặt

Tải `VinhKhang-ThuChi-Setup-1.0.1.exe` tại [GitHub Releases](https://github.com/DoanHuy002/ThuChiVK/releases/latest). Bộ cài kèm Java 21. Xem [hướng dẫn sử dụng](HUONG-DAN-SU-DUNG.md).

Dữ liệu mặc định: `D:\Codex\Projects\VinhKhangThuChiData`. Máy không có D cần cấu hình `VK_DATA_DIR` tới nơi người dùng chọn. Cập nhật phần mềm không đồng bộ dữ liệu giữa các máy. Mã nguồn và bộ cài không chứa dữ liệu Excel doanh nghiệp.

## Build Windows

Yêu cầu Node 22+, JDK 21, Maven 3.9+. Chuẩn bị JRE21 trong `runtime` bằng jlink, gồm java.base, java.desktop, java.logging, java.management, java.naming, java.net.http, java.security.jgss, java.sql, java.xml, jdk.crypto.ec, jdk.unsupported, jdk.net.

```powershell
npm ci
mvn -f backend/pom.xml package
npm run build
npm start
npm test
node tests/updates.cjs
node tests/ui.cjs
npm run package
```

Đặt `VK_PLAYWRIGHT_MODULE` tới Playwright nếu chạy UI test. Khi cài dependencies, cho phép install script của Electron. Production dùng esbuild; Vite phục vụ phát triển frontend.

## Phát hành cập nhật

Repo đã cấu hình sẵn `DoanHuy002/ThuChiVK`. Build NSIS tạo EXE, blockmap và latest.yml. Tăng version trong package.json, package-lock.json, backend/pom.xml và đường dẫn JAR cùng phiên bản, chạy kiểm thử rồi tải cả ba file lên cùng GitHub Release có tag `vX.Y.Z`. Không sửa latest.yml bằng tay. Giữ appId, đường dẫn dữ liệu và schema migration tương thích.

App tự kiểm tra khi mở và mỗi 4 giờ. Admin chọn tải, rồi app sao lưu và đóng backend trước khi cài. Client không lưu khóa GitHub. Không tải/cài tự động khi đang làm việc, không hạ phiên bản. Thử mất mạng, file tải lỗi và sao lưu lỗi trong tests/updates.cjs.

## Tài liệu

- [Database và nghiệp vụ](docs/DATABASE-VA-NGHIEP-VU.md)
- [Kiểm thử nghiệp vụ](docs/TEST-RESULTS.md)
- [Kiểm thử giao diện](docs/UI-TEST-RESULTS.md)
- Schema thực thi: backend/src/main/resources/schema.sql

Nhập Excel: chọn .xlsx trên máy, đối soát rồi chọn dòng ghi sổ. Không tự phân loại công nợ/đối tác. Các phân tích Excel doanh nghiệp giữ riêng ngoài Git.
