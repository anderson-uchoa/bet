package lps.bet.basico.validadorMgr;

public class Validador implements IValidadorMgt{
	int validadorID=0, onibusID=0;
	
	public Validador(){}
	
	public int getValidadorID(){
		return validadorID;
	}
	public int getOnibusID(){
		return onibusID;
	}
	public void setValidadorID(int id){
		validadorID = id;
	}
	public void setOnibusID(int id){
		onibusID = id;
	}
}
