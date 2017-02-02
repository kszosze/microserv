package org.microf.repository;

import org.microf.domain.IEAddress;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Cassandra repository for the IEAddress entity.
 */
@Repository
public class IEAddressRepository {

    private final Session session;

    private Mapper<IEAddress> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public IEAddressRepository(Session session) {
        this.session = session;
        this.mapper = new MappingManager(session).mapper(IEAddress.class);
        this.findAllStmt = session.prepare("SELECT * FROM iEAddress");
        this.truncateStmt = session.prepare("TRUNCATE iEAddress");
    }

    public List<IEAddress> findAll() {
        List<IEAddress> iEAddressesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                IEAddress iEAddress = new IEAddress();
                iEAddress.setId(row.getUUID("id"));
                iEAddress.setAddressline1(row.getString("addressline1"));
                iEAddress.setAddressline2(row.getString("addressline2"));
                iEAddress.setSummaryline(row.getString("summaryline"));
                iEAddress.setOrganisation(row.getString("organisation"));
                iEAddress.setStreet(row.getString("street"));
                iEAddress.setPosttown(row.getString("posttown"));
                iEAddress.setCounty(row.getString("county"));
                iEAddress.setPostcode(row.getString("postcode"));
                return iEAddress;
            }
        ).forEach(iEAddressesList::add);
        return iEAddressesList;
    }

    public IEAddress findOne(UUID id) {
        return mapper.get(id);
    }

    public IEAddress save(IEAddress iEAddress) {
        if (iEAddress.getId() == null) {
            iEAddress.setId(UUID.randomUUID());
        }
        mapper.save(iEAddress);
        return iEAddress;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
