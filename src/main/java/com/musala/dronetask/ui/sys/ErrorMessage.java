/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musala.dronetask.ui.sys;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {


    private int error;
    private String message;
    private String description;

    public ErrorMessage() {
    }


    public ErrorMessage(int error, String message, String description) {
        this.error = error;
        this.message = message;
        this.description = description;
    }
}
