package org.hare.framework.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用实体属性
 *
 * @author wang cheng
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    /**
     * 创建用户
     */
    @JsonIgnore
    @Column(updatable = false)
    private Long createUserId;
    @Column(updatable = false)
    private String createUser;
    /**
     * 创建部门
     */
    @JsonIgnore
    @Column(updatable = false)
    private Long createDeptId;
    @Column(updatable = false)
    private String createDept;
    @JsonIgnore
    @Column(updatable = false)
    private String createDeptFastId;
    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createTime;
    /**
     * 修改用户
     */
    @JsonIgnore
    private Long modifiedUserId;
    private String modifiedUser;
    /**
     * 修改部门
     */
    @JsonIgnore
    private Long modifiedDeptId;
    private String modifiedDept;
    /**
     * 修改时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedTime;
}
