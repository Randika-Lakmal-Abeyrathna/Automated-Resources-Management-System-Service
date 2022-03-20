package com.tsd.armsystem.dto;

import com.tsd.armsystem.model.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Userdto {

	private String nic;
	private String firstName;
	private String middleName;
	private String lastName;
	private String addressNo;
	private String addressStreet;
	private String addressStreet2;
	private Integer contactNumber1;
	private Integer contactNumber2;
	private String password;
	private boolean enabled;
	private Date createddate;
	private String email;
	private Instant lastmodifieddate;

	private int city;
	private Gender gender;
	private Salutation salutation;
	private Status status;
	private UserType userType;
	private ImageData imageData;
	private MaritalStatus maritalStatus;
	private Set<Role> roles = new HashSet<>();

	public Userdto() {

	}

	public Userdto(String nic, String firstName, String middleName, String lastName, String addressNo,
                   String addressStreet, String addressStreet2, Integer contactNumber1, Integer contactNumber2,
                   String password, boolean enabled, Date createddate, String email, Instant lastmodifieddate, int city,
                   Gender gender, Salutation salutation, Status status, UserType userType, ImageData imageData,
                   MaritalStatus maritalStatus, Set<Role> roles) {
		super();
		this.nic = nic;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.addressNo = addressNo;
		this.addressStreet = addressStreet;
		this.addressStreet2 = addressStreet2;
		this.contactNumber1 = contactNumber1;
		this.contactNumber2 = contactNumber2;
		this.password = password;
		this.enabled = enabled;
		this.createddate = createddate;
		this.email = email;
		this.lastmodifieddate = lastmodifieddate;
		this.city = city;
		this.gender = gender;
		this.salutation = salutation;
		this.status = status;
		this.userType = userType;
		this.imageData = imageData;
		this.maritalStatus = maritalStatus;
		this.roles = roles;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressStreet2() {
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
	}

	public Integer getContactNumber1() {
		return contactNumber1;
	}

	public void setContactNumber1(Integer contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	public Integer getContactNumber2() {
		return contactNumber2;
	}

	public void setContactNumber2(Integer contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getLastmodifieddate() {
		return lastmodifieddate;
	}

	public void setLastmodifieddate(Instant lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Salutation getSalutation() {
		return salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public ImageData getImageData() {
		return imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Userdto [nic=" + nic + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", addressNo=" + addressNo + ", addressStreet=" + addressStreet + ", addressStreet2="
				+ addressStreet2 + ", contactNumber1=" + contactNumber1 + ", contactNumber2=" + contactNumber2
				+ ", password=" + password + ", enabled=" + enabled + ", createddate=" + createddate + ", email="
				+ email + ", lastmodifieddate=" + lastmodifieddate + ", city=" + city + ", gender=" + gender
				+ ", salutation=" + salutation + ", status=" + status + ", userType=" + userType + ", imageData="
				+ imageData + ", maritalStatus=" + maritalStatus + ", roles=" + roles + "]";
	}

}
