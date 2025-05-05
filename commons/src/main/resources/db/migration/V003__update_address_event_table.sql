DO $$
    BEGIN
        IF EXISTS (
            SELECT 1
            FROM information_schema.tables
            WHERE table_name = 'events'
        ) THEN
            ALTER TABLE events
                ADD COLUMN IF NOT EXISTS zip_code     VARCHAR(64),
                ADD COLUMN IF NOT EXISTS street       VARCHAR(2024),
                ADD COLUMN IF NOT EXISTS neighborhood VARCHAR(2024),
                ADD COLUMN IF NOT EXISTS city         VARCHAR(2024),
                ADD COLUMN IF NOT EXISTS state        VARCHAR(4);
        END IF;
    END $$;
