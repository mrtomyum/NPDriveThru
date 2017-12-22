# Drive Thru 2.0
```
                                               +---------------+
                                                Drive Thru 2.0
                                                 State Diagram
                                               +---------------+

                    +      Ticket      +                   +                   +                 +                +                     +
     Order          |      Queue       |      Pickup       |       Load        |   Check-Out     |     Payment    |        Payment      |   Billing
                    |                  |                   |                   |                 |      Cash      |         Credit      |
      XXX           |                  |                   |                   |                 |                |                     |
      XXX           |                  |                   |                   |                 |                |                     |
     XXXXX          |                  |                   |                   |                 |                |                     |
    X  X  X   +------------------------------------------------------------------->            +--->            +---+                   |
       X            |                  |                   |                   |                 |                | |                   |
      XXX           |                  |                   |                   |                 |                | |                   |
     X   X          |                  |                   |                   |                 |                | |                   |
     X   X          |                  |      XXXXXX       |                   |                 |                | |   +-------------+ |
    Shopping        |                  |      X    X       |                   |                 |                | |   |             | |
                    |                  |      XXXXXX       |                   |                 |                | +-> | Member Card | |
                    |                  |   XXXXXXXXXXXX    |                   |                 |                | |   |             | |
      XXXXX         |                  |   X    XX    X    |                   |                 |                | |   +-------------+ |
      XXXXXX        |                  |   XXXXXXXXXXXX    |                   |                 |                | |                   |
XXXXXXXXXXXXX +---> +------------------->                +--->     XXXXX    +---->   +-----+   +--->   +-----+   +--+                   |
XXXXXXXXXXXXXX      |                  |  +----+--------+  |       X   X       |     |-----|     |     |-----|    |                     |
  XX      XX        |                  |  | || |  XXXXX |  |    XXXXXXXXXXX    |     ||   ||     |     ||   ||    |                     |
   Drive Thru       |                  |  | || |        |  |    X  X X X  X    |     ||   ||     |     ||   ||    |                     |
                    |                  |  | || |  XXXXX |  |     X  X X  X     |     |-----|     |     |-----|    |                     |     +-------+
                    |                  |  +-------------+  |      XXXXXXX      |     ||789||     |     ||789||    |                     |     | X |-| |
     XXXX           |    +-------+     |     +-------+     |                   |     ||456||     |     ||456||    |                     |     |   |-| |
     X  X           |    | X |-| |     |      +-----+      |      Add Item     |     ||123||     |     ||123||    |                     |     | X |-| |
    XXXXXX    +---> |    |   |-| |   +-->      +---+     +--->              +---->   +-----+   +--->   +-----+ +----+                   |     |   +-+ |
    X    X          |    | X |-| |     |        |-|        |                   |      Check      |     Payment    | |                   |     |  9999 |
XXXXXXXXXXXXXX      |    |   +-+ |     |      +-----+      |                   |       Out       |                | |      +-----+      |     vvvvvvvvv
X   Sales    X      |    |       |     |      |-----|      |                   |                 |                | |      |-----|      |        Slip
X   Order    X      |    vvvvvvvvv     |      ||   ||      |                   |                 |                | |      ||   ||      |
XXXXXXXXXXXXXX      |   Queue Order    |      ||   ||      |                   |                 |                | |      ||   ||      |
                    |                  |      |-----|      |                   |                 |                | |      |-----|      |
                    |                  |      ||789||      |                   |                 |                | |      ||789||      |
   XXXXXXX          |                  |      ||456||      |                   |                 |                | |      ||456||      |
 XX X X X XX        |  +-----------+   |      ||123||      |                   |                 |                | |      ||123||      |
XX X  X  X XX       |  |  x xxx    |   |      +-----+      |                   |                 |                | +-->   +-----+      |
X  X  X  X  X +---> |  |  x xxx    | +-->                +----------------------->            +---->              | |        SMS        |
X  X  X  X  X       |  |           |   |                   |                   |                 |                | |   Authentication  |
XX X  X  X XX       |  +-----------+   |                   |                   |                 |                | |                   |
 XX X X X XX        |  Queue Monitor   |                   |                   |                 |                | |                   |
   XXXXXXX          |                  |                   |                   |                 |                | |    +----------+   |
   On-Line          |                  |                   |                   |                 |                | |    |----------|   |
                    |                  |                   |                   |                 |                | |    ||        ||   |
                    |                  |                   |                   |                 |                | |    ||  SMS   ||   |
                    |                  |                   |                   |                 |                | |    ||        ||   |
                    |                  |                   |                   |                 |                | |    || 99999  ||   |
                    |                  |                   |                   |              +---------------------+    ||        ||   |
                    |                  |                   |                   |                 |                |      ||        ||   |
                    |                  |                   |                   |                 |                |      ||        ||   |
                    |                  |                   |                   |                 |                |      |----------|   |
                    |                  |                   |                   |                 |                |      +----------+   |
                    +                 ++                   +                   +                 +                +                     +
```

## Allow to change order Matrix (Order vs Payment Type)
เพื่อไม่ให้สับสน เรามีเงื่อนไขการอนุญาติเปลี่ยนแปลงรายการสินค้าในตะกร้า เพื่อป้องกันกรณีผู้รับสินค้ากับผู้สั่งเป็นคนละคนกัน เช่นให้คนขับรถมารับสินค้า 

- ต้องห้ามเปลี่ยนแปลงรายการ และหากจัดสินค้าได้ไม่ครบ ก็จะต้องหยิบของออกจาตะกร้าก่อนจ่ายเงิน 
- การขายเงื่อนเชื่อการเปลี่ยนแปลงรายการสามารถทำได้ตราบใดที่ไม่เกินวงเงิน

ทั้งนี้คือให้ยึดหลักการขอตะกร้าชอปปิ้งเสมอ

| Order/Payment   | Cash  | Credit |
| --------------- | :---: | :----: |
| Shopping        | Y     | Y      |
| Drive-Thru      | Y     | Y      |
| Sale Order      | Y     | N      |
| Online Pickup   | Y     | N      |
| Online Delivery | N     | N      |
