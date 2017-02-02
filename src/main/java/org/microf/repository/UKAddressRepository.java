package org.microf.repository;

import org.microf.domain.UKAddress;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the UKAddress entity.
 */
@Repository
public class UKAddressRepository {

    private final Session session;

    private Mapper<UKAddress> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public UKAddressRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(UKAddress.class);
        this.findAllStmt = session.prepare("SELECT * FROM uKAddress");
        this.truncateStmt = session.prepare("TRUNCATE uKAddress");
    }

    public List<UKAddress> findAll() {
        List<UKAddress> uKAddressesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                UKAddress uKAddress = new UKAddress();
                uKAddress.setId(row.getUUID("id"));
                uKAddress.setSummaryline(row.getString("summaryline"));
                uKAddress.setOrganisation(row.getString("organisation"));
                uKAddress.setBuildingname(row.getString("buildingname"));
                uKAddress.setPremise(row.getString("premise"));
                uKAddress.setStreet(row.getString("street"));
                uKAddress.setDependentlocality(row.getString("dependentlocality"));
                uKAddress.setPosttown(row.getString("posttown"));
                uKAddress.setCounty(row.getString("county"));
                uKAddress.setPostcode(row.getString("postcode"));
                return uKAddress;
            }
        ).forEach(uKAddressesList::add);
        return uKAddressesList;
    }

    public UKAddress findOne(UUID id) {
        return mapper.get(id);
    }

    public UKAddress save(UKAddress uKAddress) {
        if (uKAddress.getId() == null) {
            uKAddress.setId(UUID.randomUUID());
        }
        mapper.save(uKAddress);
        return uKAddress;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
