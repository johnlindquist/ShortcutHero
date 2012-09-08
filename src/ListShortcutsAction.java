import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.impl.ActionManagerImpl;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.impl.KeymapManagerImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: johnlindquist
 * Date: 9/5/12
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListShortcutsAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        ShortcutSet shortcutSet = getShortcutSet();
        Shortcut[] shortcuts = shortcutSet.getShortcuts();

        Keymap activeKeymap = KeymapManagerImpl.getInstance().getActiveKeymap();
        String[] actionIds = activeKeymap.getActionIds();
        ActionManager actionManager = ActionManagerImpl.getInstance();

        List<String> shortcutsOut = new ArrayList<String>();
        for (String actionId : actionIds) {
            Shortcut[] shortcuts1 = activeKeymap.getShortcuts(actionId);
            AnAction action = actionManager.getAction(actionId);
            for (Shortcut shortcut : shortcuts1) {

                if (action != null)
                {
                    String fullName = shortcut.toString() + "," + action.toString();
                    fullName = fullName.replaceAll("\\[", "");
                    fullName = fullName.replaceAll("\\]", "");
                    fullName = fullName.replaceAll("pressed ", "");
                    shortcutsOut.add(fullName);
                }

            }
        }


        Collections.sort(shortcutsOut);
        for (String s : shortcutsOut) {
            System.out.println(s);
        }
    }
}
