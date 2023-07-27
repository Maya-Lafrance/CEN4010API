//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.geekText.geekText.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "Wishlist"
)
public class Wishlist {
    @Id
    @Column(
            name = "ListID"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int ListID;
    @Column(
            name = "UserID"
    )
    private int UserID;
    @Column(
            name = "BookID"
    )
    private int BookID;
    @Column(
            name = "ListName"
    )
    private String ListName;

    public int getListID() {
        return this.ListID;
    }

    public int getUserID() {
        return this.UserID;
    }

    public int getBookID() {
        return this.BookID;
    }

    public String getListName() {
        return this.ListName;
    }

    public void setListID(final int ListID) {
        this.ListID = ListID;
    }

    public void setUserID(final int UserID) {
        this.UserID = UserID;
    }

    public void setBookID(final int BookID) {
        this.BookID = BookID;
    }

    public void setListName(final String ListName) {
        this.ListName = ListName;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Wishlist)) {
            return false;
        } else {
            Wishlist other = (Wishlist)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getListID() != other.getListID()) {
                return false;
            } else if (this.getUserID() != other.getUserID()) {
                return false;
            } else if (this.getBookID() != other.getBookID()) {
                return false;
            } else {
                Object this$ListName = this.getListName();
                Object other$ListName = other.getListName();
                if (this$ListName == null) {
                    if (other$ListName != null) {
                        return false;
                    }
                } else if (!this$ListName.equals(other$ListName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Wishlist;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getListID();
        result = result * 59 + this.getUserID();
        result = result * 59 + this.getBookID();
        Object $ListName = this.getListName();
        result = result * 59 + ($ListName == null ? 43 : $ListName.hashCode());
        return result;
    }

    public String toString() {
        int var10000 = this.getListID();
        return "Wishlist(ListID=" + var10000 + ", UserID=" + this.getUserID() + ", BookID=" + this.getBookID() + ", ListName=" + this.getListName() + ")";
    }

    public Wishlist() {
    }

    public Wishlist(final int ListID, final int UserID, final int BookID, final String ListName) {
        this.ListID = ListID;
        this.UserID = UserID;
        this.BookID = BookID;
        this.ListName = ListName;
    }
}
