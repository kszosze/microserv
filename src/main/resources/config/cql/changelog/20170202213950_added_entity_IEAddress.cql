
CREATE TABLE IF NOT EXISTS iEAddress (
    id uuid,
    addressline1 text,
    addressline2 text,
    summaryline text,
    organisation text,
    street text,
    posttown text,
    county text,
    postcode text,
    PRIMARY KEY(id)
);
