package json;

public class KeyValueMap {
        
        public class Item{
            private  String key;
            private  Object value;
            private Item next;
            
            
            public Item(String key, Object value) {
                this.key = key;
                this.value = value;
                
            }
            public void setValue(Object val) {
                this.value = val;
            }
            public void setNext(Item next) {
                this.next = next;
            }
            public Item getNext() {
                return next;
            }
            public boolean equalkey(String key) {
                return key.equals(this.key);
            }
            public Object getValue() {
                return this.value;
            }
            public String getKey() {
            	return key;
            }
           
        }
        private Item head = null;
        private Item last = null;
        
        public  KeyValueMap() {
        }
       
        private Item findItem(String key) {
            if (head == null) return null;
            Item nextitem = head;
            while (nextitem.getNext()!=null) {
                if (nextitem.equalkey(key))
                {
                    return nextitem;
                }
                nextitem = nextitem.getNext();
            }
            return null;
        }
        public Item getlast() {
            return last;
        }
        public Item gethead() {
        	return head;
        }
        public boolean add(String key, Object value) {
            Item itm=findItem(key);
        	if (itm != null)
            {
        		itm.setValue(value);
                return false;
            }
            else {
                if (head==null) {
                    head = new Item(key, value);
                    last = head;
                }
                else
                {
                    last.setNext(new Item(key, value));
                    last = last.getNext();
                }
                
                return true;
            }
        }
        public Object getValue(String key) {

            if (head == null) return 0;
            Item nextitem = head;
            while (nextitem != null)
            {
                if (nextitem.equalkey(key))
                {
                    return nextitem.getValue();
                }
                nextitem = nextitem.getNext();

            }
            return 0;
        }
        
       
    }
