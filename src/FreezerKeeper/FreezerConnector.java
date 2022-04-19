package FreezerKeeper;

import java.util.List;
import java.sql.*;

public class FreezerConnector {
    public List<UserItem> itemSelectAll() {
        Connection con = getConnection();

        List<UserItem> itemSelectAll = bd.itemSelectAll(con);

        close(con);

        return bookSelectAll;
    }
}
