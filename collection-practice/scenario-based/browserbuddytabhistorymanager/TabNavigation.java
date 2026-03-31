package browserbuddytabhistorymanager;

abstract class TabNavigation {
abstract void addhistory(Tab data);
abstract void backhistory();
abstract void forwardhistory();
abstract void showcurrenthistory();
abstract void closeTab(Tab data);
abstract void restoreTab();
}
