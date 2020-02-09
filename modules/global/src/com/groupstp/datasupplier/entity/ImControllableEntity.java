package com.groupstp.datasupplier.entity;

/**
 * Entity who implements this interface support control reverting mechanism with ChangeQuery
 *
 * @author adiatullin
 * //@see com.groupstp.rtneoimport.entity.ChangeQuery
 */
public interface ImControllableEntity {
    /**
     * @return is current entity under control processing or not
     */
    Boolean getCheckProcessing();

    void setCheckProcessing(Boolean processing);

    /**
     * @return last control processing comment
     */
    String getCheckComment();

    void setCheckComment(String comment);
}
