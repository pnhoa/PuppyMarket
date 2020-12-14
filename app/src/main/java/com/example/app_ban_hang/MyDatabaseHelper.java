package com.example.app_ban_hang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhuhoa.puppystore.model.CartProduct;
import com.nhuhoa.puppystore.model.OrderInformation;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "puppyMarket.db";
    // Table name: Note.
    private static final String TABLE_CART = "Cart";
    private static final String TABLE_ORDER = "Order";

    private static final String COLUMN_CART_ID_ORDER = "orderId";
    private static final String COLUMN_CART_ID_PRODUCT = "productId";
    private static final String COLUMN_CART_QUANTITY = "quantity";
    private static final String COLUMN_CART_TOTAL_PRICE = "totalPrice";

    private static final String COLUMN_ORDER_ID_PURCHASE ="purchaseId";
    private static final String COLUMN_ORDER_ID_ORDER ="orderId";
    private static final String COLUMN_ORDER_ID_USER ="userId";
    private static final String COLUMN_ORDER_USERNAME ="username";
    private static final String COLUMN_ORDER_EMAIL ="email";
    private static final String COLUMN_ORDER_PHONE ="phone";
    private static final String COLUMN_ORDER_ADDRESS ="address";
    private static final String COLUMN_ORDER_TOTAL_PAYMENT ="totalPayment";
    private static final String COLUMN_ORDER_PAYMENT_METHOD ="paymentMethod";
    private static final String COLUMN_ORDER_PURCHASE_TIME="purchaseTime";

    private static final String CREATE_TABLE_CART = "CREATE TABLE '" + TABLE_CART + "'("
            + COLUMN_CART_ID_ORDER + " INTEGER,"
            + COLUMN_CART_ID_PRODUCT + " INTEGER," + COLUMN_CART_QUANTITY + " INTEGER," + COLUMN_CART_TOTAL_PRICE + " REAL" + ")";

    private static final String CREATE_TABLE_ORDER = "CREATE TABLE '" + TABLE_ORDER + "'("
            + COLUMN_ORDER_ID_PURCHASE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ORDER_ID_ORDER + " INTEGER," + COLUMN_ORDER_ID_USER + " INTEGER,"
            + COLUMN_ORDER_USERNAME + " TEXT,"+ COLUMN_ORDER_EMAIL + " TEXT,"
            + COLUMN_ORDER_PHONE + " TEXT," +COLUMN_ORDER_ADDRESS + " TEXT,"
            + COLUMN_ORDER_TOTAL_PAYMENT + " REAL," +COLUMN_ORDER_PAYMENT_METHOD + " TEXT,"
            + COLUMN_ORDER_PURCHASE_TIME + " TEXT" + ")";
    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");

        sqLiteDatabase.execSQL(CREATE_TABLE_CART);
        sqLiteDatabase.execSQL(CREATE_TABLE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_CART + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_ORDER + "'");

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void addOrder(OrderInformation order) {
        Log.i(TAG, "MyDatabaseHelper.addInformation ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID_ORDER, order.getOrderId());
        values.put(COLUMN_ORDER_ID_USER, order.getUserId());
        values.put(COLUMN_ORDER_USERNAME, order.getUsername());
        values.put(COLUMN_ORDER_EMAIL, order.getEmail());
        values.put(COLUMN_ORDER_PHONE, order.getPhone());
        values.put(COLUMN_ORDER_ADDRESS, order.getAddress());
        values.put(COLUMN_ORDER_TOTAL_PAYMENT, order.getTotalPayment());
        values.put(COLUMN_ORDER_PAYMENT_METHOD, order.getPaymentMethod());
        values.put(COLUMN_ORDER_PURCHASE_TIME, order.getPurchaseTime());


        // Inserting Row
        db.insert(TABLE_ORDER, null, values);

        // Closing database connection
        db.close();
    }


    public OrderInformation getOrder(int id) {
        Log.i(TAG, "MyDatabaseHelper.getInformation ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDER, new String[] {
                        COLUMN_ORDER_ID_PURCHASE,
                        COLUMN_ORDER_ID_ORDER ,
                        COLUMN_ORDER_ID_USER ,
                        COLUMN_ORDER_USERNAME ,
                        COLUMN_ORDER_EMAIL ,
                        COLUMN_ORDER_PHONE ,
                        COLUMN_ORDER_ADDRESS ,
                        COLUMN_ORDER_TOTAL_PAYMENT ,
                        COLUMN_ORDER_PAYMENT_METHOD ,
                        COLUMN_ORDER_PURCHASE_TIME }, COLUMN_ORDER_ID_PURCHASE + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        OrderInformation order = new OrderInformation(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                Double.parseDouble(cursor.getString(7)),
                cursor.getString(8),
                cursor.getString(9));
        // return note
        return order;
    }


    public List<OrderInformation> getAllOrders() {
        Log.i(TAG, "MyDatabaseHelper.getAllOrders ... " );

        List<OrderInformation> orderList = new ArrayList<OrderInformation>();
        // Select All Query
        String selectQuery = "SELECT  * FROM '" + TABLE_ORDER+ "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                OrderInformation order = new OrderInformation();
                order.setPurchaseId(Integer.parseInt(cursor.getString(0)));
                order.setOrderId(Integer.parseInt(cursor.getString(1)));
                order.setUserId(Integer.parseInt(cursor.getString(2)));
                order.setUsername(cursor.getString(3));
                order.setEmail(cursor.getString(4));
                order.setPhone(cursor.getString(5));
                order.setAddress(cursor.getString(6));
                order.setTotalPayment(Double.parseDouble(cursor.getString(7)));
                order.setPaymentMethod(cursor.getString(8));
                order.setPurchaseTime(cursor.getString(9));
                // Adding note to list
                orderList.add(order);
            } while (cursor.moveToNext());
        }

        // return note list
        return orderList;
    }

    public int getOrdersCount() {
        Log.i(TAG, "MyDatabaseHelperInfor.getInforsCount ... " );

        String countQuery = "SELECT  * FROM '" + TABLE_ORDER+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateOrder(OrderInformation order) {
        Log.i(TAG, "MyDatabaseHelperInfor.updateInfor ... " );

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID_ORDER, order.getOrderId());
        values.put(COLUMN_ORDER_ID_USER, order.getUserId());
        values.put(COLUMN_ORDER_USERNAME, order.getUsername());
        values.put(COLUMN_ORDER_EMAIL, order.getEmail());
        values.put(COLUMN_ORDER_PHONE, order.getPhone());
        values.put(COLUMN_ORDER_ADDRESS, order.getAddress());
        values.put(COLUMN_ORDER_TOTAL_PAYMENT, order.getTotalPayment());
        values.put(COLUMN_ORDER_PAYMENT_METHOD, order.getPaymentMethod());
        values.put(COLUMN_ORDER_PURCHASE_TIME, order.getPurchaseTime());

        // updating row
        return db.update(TABLE_ORDER, values, COLUMN_ORDER_ID_PURCHASE + " = ?",
                new String[]{String.valueOf(order.getPurchaseId())});
    }

    public void deleteOrder(OrderInformation order) {
        Log.i(TAG, "MyDatabaseHelperInfor.deleteInfor ... " );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDER, COLUMN_ORDER_ID_PURCHASE + " = ?",
                new String[] { String.valueOf(order.getPurchaseId()) });
        db.close();
    }
    public void addToCart(CartProduct cart) {
        Log.i(TAG, "MyDatabaseHelper.addToCart ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CART_ID_ORDER, cart.getOrderId());
        values.put(COLUMN_CART_ID_PRODUCT, cart.getProductId());
        values.put(COLUMN_CART_QUANTITY, cart.getQuantity());
        values.put(COLUMN_CART_TOTAL_PRICE, cart.getTotalPrice());
        // Inserting Row
        db.insert(TABLE_CART, null, values);

        // Closing database connection
        db.close();
    }
    public CartProduct getCart(int id) {
        Log.i(TAG, "MyDatabaseHelper.getCart ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDER, new String[] {
                        COLUMN_CART_ID_ORDER ,
                        COLUMN_CART_ID_PRODUCT ,
                        COLUMN_CART_QUANTITY ,
                        COLUMN_CART_TOTAL_PRICE
                        }, COLUMN_CART_ID_ORDER  + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

            CartProduct cart = new CartProduct(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)),
                Double.parseDouble(cursor.getString(3)));
        // return note
        return cart;
    }
    public List<CartProduct> getAllCarts() {
        Log.i(TAG, "MyDatabaseHelper.getAllCarts ... " );

        List<CartProduct> cartList = new ArrayList<CartProduct>();
        // Select All Query
        String selectQuery = "SELECT  * FROM '" + TABLE_CART+ "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CartProduct cart = new CartProduct();

                cart.setOrderId(Integer.parseInt(cursor.getString(0)));
                cart.setProductId(Integer.parseInt(cursor.getString(1)));
                cart.setQuantity(Integer.parseInt(cursor.getString(2)));
                cart.setTotalPrice(Double.parseDouble(cursor.getString(3)));
                // Adding note to list
                cartList.add(cart);
            } while (cursor.moveToNext());
        }

        // return note list
        return cartList;
    }
    public int getCartsCount() {
        Log.i(TAG, "MyDatabaseHelper.getCartsCount ... " );

        String countQuery = "SELECT  * FROM '" + TABLE_CART +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }
    public int updateCart(CartProduct cart) {
        Log.i(TAG, "MyDatabaseHelper.updateCart ... " );

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CART_ID_ORDER, cart.getOrderId());
        values.put(COLUMN_CART_ID_PRODUCT, cart.getProductId());
        values.put(COLUMN_CART_QUANTITY, cart.getQuantity());
        values.put(COLUMN_CART_TOTAL_PRICE, cart.getTotalPrice());

        // updating row
        return db.update(TABLE_CART, values, COLUMN_CART_ID_ORDER + " = ?",
                new String[]{String.valueOf(cart.getOrderId())});
    }

    public void deleteCart(CartProduct cart) {
        Log.i(TAG, "MyDatabaseHelper.deleteCart ... " );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_CART_ID_ORDER + " = ?",
                new String[]{String.valueOf(cart.getOrderId())});
        db.close();
    }


}

