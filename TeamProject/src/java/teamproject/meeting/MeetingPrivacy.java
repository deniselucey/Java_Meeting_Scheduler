/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.meeting;

/**
 *
 * @author Nigel
 */
public enum MeetingPrivacy
{
    PRIVATE((byte)1),
    PUBLIC((byte)2),
    FRIENDS((byte)3);
    
    final byte id;
    
    private MeetingPrivacy(byte id)
    {
        this.id = id;
    }
    
    public byte getId()
    {
        return id;
    }
    
    
}
