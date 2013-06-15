package lb2.com.br.util;

import lb2.com.br.model.TableSpace;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bernardovale
 * Date: 15/06/13
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class TablespaceUtil {
    /**
     * Find the index of a tablespace name inside the array
     * @param tableSpaces
     * @param name
     * @return
     */
    public static int tbNameIndex(List<TableSpace> tableSpaces, String name){
        for(int i=0; i<tableSpaces.size();i++){
            if(tableSpaces.get(i).getNome().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }
}
