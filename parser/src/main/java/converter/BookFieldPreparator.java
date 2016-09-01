package converter;

class BookFieldPreparator {

    String validateField(String value, int length) {
        if(value == null) {
            return "";
        }

        if(value.length() > length) {
            return value.substring(0, length);
        }

        return value;
    }

}