package musicplayer.form;

import musicplayer.entity.MasterGender;
import musicplayer.entity.User;
import musicplayer.entity.UserAddress;

public class UserForm {
	private User User;

	public UserForm(musicplayer.entity.User user, UserAddress userAddress1, UserAddress userAddress2) {
		User = user;
		//this.gender = gender;
		UserAddress1 = userAddress1;
		UserAddress2 = userAddress2;
	}

	private MasterGender gender;
	private UserAddress UserAddress1;
	private UserAddress UserAddress2;


	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public MasterGender getGender() {
		return gender;
	}

	public void setGender(MasterGender gender) {
		gender = gender;
	}

	public UserAddress getUserAddress1() {
		return UserAddress1;
	}

	public void setUserAddress1(UserAddress address) {
		UserAddress1 = address;
	}


	public UserAddress getUserAddress2() {
		return UserAddress2;
	}

	public void setUserAddress2(UserAddress address) {
		UserAddress2 = address;
	}

}
