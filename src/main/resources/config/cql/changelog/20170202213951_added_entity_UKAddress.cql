
CREATE TABLE IF NOT EXISTS uKAddress (
    id uuid,
    summaryline text,
    organisation text,
    buildingname text,
    premise text,
    street text,
    dependentlocality text,
    posttown text,
    county text,
    postcode text,
    PRIMARY KEY(id)
);
