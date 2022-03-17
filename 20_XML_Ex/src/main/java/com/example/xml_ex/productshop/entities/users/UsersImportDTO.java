package com.example.xml_ex.productshop.entities.users;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportDTO {

    @XmlElement(name = "user")
    private List<UserImportDTO> users;

    public UsersImportDTO() {}

    public List<UserImportDTO> getUsers() {
        return users;
    }
}
