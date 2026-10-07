ALTER TABLE funds ADD COLUMN bank_name TEXT NOT NULL DEFAULT '';
ALTER TABLE funds ADD COLUMN account_number TEXT NOT NULL DEFAULT '';
ALTER TABLE funds ADD COLUMN account_holder TEXT NOT NULL DEFAULT '';
ALTER TABLE contacts ADD COLUMN attendance_code TEXT NOT NULL DEFAULT '';
CREATE UNIQUE INDEX attendance_code_unique ON contacts(attendance_code COLLATE NOCASE) WHERE kind='EMPLOYEE' AND attendance_code<>'';
CREATE TABLE attendance(id INTEGER PRIMARY KEY AUTOINCREMENT,employee_id INTEGER NOT NULL REFERENCES contacts(id),date TEXT NOT NULL,status TEXT NOT NULL CHECK(status IN ('WORK','HALF','PAID_LEAVE','UNPAID_LEAVE','OFF')),overtime_minutes INTEGER NOT NULL DEFAULT 0 CHECK(overtime_minutes>=0 AND overtime_minutes<=960 AND overtime_minutes%15=0),note TEXT NOT NULL DEFAULT '',source TEXT NOT NULL DEFAULT 'MANUAL',device_record_key TEXT UNIQUE,created_by INTEGER NOT NULL REFERENCES users(id),updated_by INTEGER NOT NULL REFERENCES users(id),deleted INTEGER NOT NULL DEFAULT 0,version INTEGER NOT NULL DEFAULT 1,updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,UNIQUE(employee_id,date));
CREATE INDEX attendance_date ON attendance(date,deleted);
CREATE TABLE attendance_months(month TEXT PRIMARY KEY,locked INTEGER NOT NULL DEFAULT 0 CHECK(locked IN (0,1)),version INTEGER NOT NULL DEFAULT 1,updated_by INTEGER NOT NULL REFERENCES users(id),updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP);
PRAGMA user_version=2;
