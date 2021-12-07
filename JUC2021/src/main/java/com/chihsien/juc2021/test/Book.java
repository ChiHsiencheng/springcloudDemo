package com.chihsien.juc2021.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book
{
    private Integer id;
    private String  bookName;
    private double  price;
    private String  author;
}
