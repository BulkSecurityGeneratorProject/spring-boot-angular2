package com.naddame.config.dbmigrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.naddame.model.util.Names;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "001")
public class InitialSetupMigration {

    private Map<String, String>[] authoritiesUser = new Map[] { new HashMap<>() };

    private Map<String, Object>[] items = new Map[] { new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>() };

    {
        items[0].put("itemId", "804");
        items[0].put("label", "Drink");
        items[0].put("price", 9);


        items[1].put("itemId", "131");
        items[1].put("label", "Coffee");
        items[1].put("price", 30);

        items[2].put("itemId", "140");
        items[2].put("label", "Cake");
        items[2].put("price", 50);

        items[3].put("itemId", "22");
        items[3].put("label", "Salad");
        items[3].put("price", 35);

        items[4].put("itemId", "74");
        items[4].put("label", "Desert");
        items[4].put("price", 12);

        items[5].put("itemId", "337");
        items[5].put("label", "Coffee");
        items[5].put("price", 30);

    }

    private Map<String, String>[] authoritiesAdminAndUser = new Map[] { new HashMap<>(), new HashMap<>() };

    {
        authoritiesUser[0].put("_id", "ROLE_USER");
        authoritiesAdminAndUser[0].put("_id", "ROLE_USER");
        authoritiesAdminAndUser[1].put("_id", "ROLE_ADMIN");
    }

    @ChangeSet(order = "01", author = "initiator", id = "01-addAuthorities")
    public void addAuthorities(DB db) {
        DBCollection authorityCollection = db.getCollection("nad_authority");
        authorityCollection.insert(
            BasicDBObjectBuilder.start()
                .add("_id", "ROLE_ADMIN")
                .get());
        authorityCollection.insert(
            BasicDBObjectBuilder.start()
                .add("_id", "ROLE_USER")
                .get());
    }

    @ChangeSet(order = "02", author = "initiator", id = "02-addUsers")
    public void addUsers(DB db) {
        DBCollection usersCollection = db.getCollection("nad_users");
        usersCollection.createIndex("login");
        usersCollection.createIndex("email");
        usersCollection.insert(BasicDBObjectBuilder.start()
            .add("_id", "user-0")
            .add("login", "system")
            .add("password", "$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG")
            .add("first_name", "")
            .add("last_name", "System")
            .add("email", "system@localhost")
            .add("activated", "true")
            .add("lang_key", "en")
            .add("created_by", "system")
            .add("created_date", new Date())
            .add("authorities", authoritiesAdminAndUser)
            .get()
        );
        usersCollection.insert(BasicDBObjectBuilder.start()
            .add("_id", "user-1")
            .add("login", "anonymousUser")
            .add("password", "$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO")
            .add("first_name", "Anonymous")
            .add("last_name", "User")
            .add("email", "anonymous@localhost")
            .add("activated", "true")
            .add("lang_key", "en")
            .add("created_by", "system")
            .add("created_date", new Date())
            .add("authorities", new Map[]{})
            .get()
        );
        usersCollection.insert(BasicDBObjectBuilder.start()
            .add("_id", "user-2")
            .add("login", "admin")
            .add("password", "$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC")
            .add("first_name", "admin")
            .add("last_name", "Administrator")
            .add("email", "admin@localhost")
            .add("activated", "true")
            .add("lang_key", "en")
            .add("created_by", "system")
            .add("created_date", new Date())
            .add("authorities", authoritiesAdminAndUser)
            .get()
        );
        usersCollection.insert(BasicDBObjectBuilder.start()
            .add("_id", "user-3")
            .add("login", "user")
            .add("password", "$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K")
            .add("first_name", "")
            .add("last_name", "User")
            .add("email", "user@localhost")
            .add("activated", "true")
            .add("lang_key", "en")
            .add("created_by", "system")
            .add("created_date", new Date())
            .add("authorities", authoritiesUser)
            .get()
        );
    }

    @ChangeSet(order = "03", author = "initiator", id = "03-addOrders")
    public void addOrders(DB db) {
        DBCollection usersCollection = db.getCollection("nad_orders");
        usersCollection.insert(BasicDBObjectBuilder.start()
                .add("_id", 0L)
                .add("orderId", "238b")
                .add("created", Date.from(ZonedDateTime.parse("2017-01-13T15:49:03.740Z").toInstant()))
                .add("diners", 1)
                .add("paid", false)
                .add("items", items)
                .add("creator", "system")
                .add("modifier", "My system")
                .get()
        );
        usersCollection.insert(BasicDBObjectBuilder.start()
                .add("_id", 1L)
                .add("orderId", "39be")
                .add("created", Date.from(ZonedDateTime.parse("2017-01-13T15:49:03.740Z").toInstant()))
                .add("diners", 1)
                .add("paid", false)
                .add("items", items)
                .add("comments", "My Comments")
                .add("creator", "system")
                .add("modifier", "My system")
                .get()
        );

        usersCollection.insert(BasicDBObjectBuilder.start()
                .add("_id", 2L)
                .add("orderId", "db7f")
                .add("created", Date.from(ZonedDateTime.parse("2017-01-20T14:06:28.524Z").toInstant()))
                .add("diners", 1)
                .add("paid", true)
                .add("comments", "My Comments")
                .add("creator", "system")
                .add("modifier", "My system")
                .get()
        );
    }

    @ChangeSet(order = "04", author = "initiator", id = "04-initSequence")
    public void initSequence(DB db) {
        DBCollection usersCollection = db.getCollection("nad_sequence");
        usersCollection.insert(BasicDBObjectBuilder.start()
                .add("_id", Names.order_seq)
                .add("seq", 4L)
                .get()
        );
    }
}
