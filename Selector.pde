interface Selector{
	final static int SELECTING=0;
	final static int RUNNING  =1;
	void addSelectable(Selectable selectable);
	void next(); 
	void prev(); 
	void open(); 
	void onClose(Selectable selectable);
}
