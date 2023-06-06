package com.restful.booker.model;

import java.util.HashMap;

public class CustomerPojo {

    private String firstName;
    private String lastName;
    private String email;
    private int totalprice;
    private boolean depositpaid;
    private HashMap<String,Object> bookingdates;
    private String additionalneeds;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalPrice() {
        return totalprice;
    }

    public void setTotalPrice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositPaid() {
        return depositpaid;
    }

    public void setDepositPaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public HashMap<String, Object> getBookingDates() {
        return bookingdates;
    }

    public void setBookingDates(HashMap<String, Object> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalNeeds() {
        return additionalneeds;
    }

    public void setAdditionalNeeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }



    public static CustomerPojo getCustomersPojo(String firstName, String lastName, String email, int totalprice,
                                               boolean depositpaid,HashMap<String, Object> bookingdates,String additionalneeds){
        CustomerPojo customerPojo = new CustomerPojo();


        customerPojo.setFirstName(firstName);
        customerPojo.setLastName(lastName);
        customerPojo.setEmail(email);
        customerPojo.setTotalPrice(totalprice);
        customerPojo.setDepositPaid(depositpaid);
        customerPojo.setBookingDates(bookingdates);
        customerPojo.setAdditionalNeeds(additionalneeds);
        return customerPojo;
    }

    public static CustomerPojo getCustomerPojo(String name){
        CustomerPojo customerPojo = new CustomerPojo();
        customerPojo.setEmail(name);
        return customerPojo;
    }


}
