/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signon;

/**
 *
 * @author S531749
 */
public class Condition {

    static enum Info {
        NAME, ID, HOURS, MEETINGS;
    };

    static enum Type {
        X, EQUALS, CONTAINS, GREATER_THAN, LESS_THAN
    };

    Info info;
    Type type;

    public Condition(Info info) {
        this.info = info;
    }

    public void setType(Type type) {
        this.type = type;
        //System.out.println(type);
    }

    public boolean isMet(Member member, String in) {
        if (info.equals(Info.NAME)) {
            switch (type) {
                case X:
                    return true;
                case EQUALS:
                    return (member.getName().equalsIgnoreCase(in));
                case CONTAINS:
                    return (member.getName().contains(in));
                default:
                    break;
            }
        }
        return false;
    }

    public boolean isMet(Member member, double in) {
        if (info.equals(Info.ID)) {
            int memberID = Integer.parseInt(member.getId());
            switch (type) {
                case X:
                    return true;
                case EQUALS:
                    return (memberID == in);
                case GREATER_THAN:
                    return (memberID > in);
                case LESS_THAN:
                    return (memberID < in);
                default:
                    break;
            }
        } else if (info.equals(Info.HOURS)) {
            switch (type) {
                case X:
                    return true;
                case EQUALS:
                    return (member.getTotalTimeDouble() == in);
                case GREATER_THAN:
                    return (member.getTotalTimeDouble() > in);
                case LESS_THAN:
                    return (member.getTotalTimeDouble() < in);
                default:
                    break;
            }
        } else if (info.equals(Info.MEETINGS)){
            
            switch (type) {
                case X:
                    return true;
                case EQUALS:
                    return (member.getNumberOfMeetings() == in);
                case GREATER_THAN:
                    //System.out.println(member.toString() + " In: " + in + " Num: "+ member.getNumberOfMeetings());
                    return (member.getNumberOfMeetings() > in);
                case LESS_THAN:
                    return (member.getNumberOfMeetings() < in);
                default:
                    break;
            }
        }
        return false;
    }

    public String getInfoString() {
        String ret = info.toString();
        //String ret = s.charAt(0) + s.toLowerCase().substring(1);
        while (ret.length() < 8) {
            ret += " ";
        }
        return ret;
    }

    public Type[] getStringTypes() {
        Type[] toRet = {Type.X, Type.EQUALS, Type.CONTAINS};
        return toRet;
    }

    public Type[] getDoubleTypes() {
        Type[] toRet = {Type.X, Type.EQUALS, Type.GREATER_THAN, Type.LESS_THAN};
        return toRet;
    }
}