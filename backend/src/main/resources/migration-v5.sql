CREATE TABLE supplier_adjustments(id INTEGER PRIMARY KEY AUTOINCREMENT,contact_id INTEGER NOT NULL REFERENCES contacts(id),date TEXT NOT NULL,description TEXT NOT NULL,amount INTEGER NOT NULL CHECK(amount>0 AND amount<=1000000000000),source_key TEXT UNIQUE,deleted INTEGER NOT NULL DEFAULT 0,version INTEGER NOT NULL DEFAULT 1);
ALTER TABLE warehouse_links ADD COLUMN adjustment_id INTEGER REFERENCES supplier_adjustments(id);
ALTER TABLE warehouse_links ADD COLUMN pending_json TEXT NOT NULL DEFAULT '';
CREATE INDEX supplier_adjustment_contact ON supplier_adjustments(contact_id,deleted,date);
PRAGMA user_version=5;
