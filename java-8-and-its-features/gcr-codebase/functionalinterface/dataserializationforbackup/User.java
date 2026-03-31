package dataserializationforbackup;

class User implements BackupSerializable {
private String name;
private String email;
User(String name,String email){
	this.name=name;
	this.email=email;
}
public String getname() {return name;}
public String getemail() {return email;}

}
