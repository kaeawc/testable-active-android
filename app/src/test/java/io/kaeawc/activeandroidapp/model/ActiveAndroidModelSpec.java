package io.kaeawc.activeandroidapp.model;

import static org.mockito.Mockito.*;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.content.ContentProvider;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Base for tests that involve active android library calls and classes.
 *
 * It makes heavy use of PowerMock
 * to overcome the static nature of ActiveAndroid library. This library,
 * while related, does not directly use any of the Android API, so it uses
 * standard jUnit approach.
 */
@PrepareForTest({ Cache.class, TableInfo.class, Context.class,
        ContentResolver.class, ContentProvider.class, ContentValues.class })
@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("com.activeandroid.content.ContentProvider")
public abstract class ActiveAndroidModelSpec {

    /** Dummy table name. */
    protected static final String TABLE = "TestTable";

    /** Mocked android context - to be able to inject stuff into ActiveAndroid internals. */
    protected Context context;

    /** Mocked sqlite database. */
    protected SQLiteDatabase sqliteDb;

    /** Mocked table info. */
    protected TableInfo tableInfo;

    /**
     * Override this to add further setup code in subclasses.
     */
    protected void onSetup() {}

    /**
     * Setups a number of mocks, parts of them static, to be able to push things
     * into ActiveAndroid internals, making simple tests possible.
     *
     * @throws Exception
     *					in case of an error - should not happen
     */
    @Before
    public void setup() throws Exception {
        PowerMockito.mockStatic(Cache.class);
        PowerMockito.mockStatic(ContentProvider.class);
        tableInfo = PowerMockito.mock(TableInfo.class);
        context = PowerMockito.mock(Context.class);
        ContentResolver resolver = PowerMockito.mock(ContentResolver.class);
        sqliteDb = PowerMockito.mock(SQLiteDatabase.class);
        ContentValues vals = PowerMockito.mock(ContentValues.class);
        PowerMockito.whenNew(ContentValues.class).withNoArguments().thenReturn(vals);
        PowerMockito.when(Cache.openDatabase()).thenReturn(sqliteDb);
        when(context.getContentResolver()).thenReturn(resolver);
        doNothing().when(resolver).notifyChange(any(Uri.class), any(ContentObserver.class));
        when(tableInfo.getFields()).thenReturn(new ArrayList<Field>());
        when(tableInfo.getTableName()).thenReturn(TABLE);
        PowerMockito.when(Cache.getTableInfo(any(Class.class))).thenReturn(tableInfo);
        PowerMockito.when(Cache.getContext()).thenReturn(context);
        PowerMockito.when(ContentProvider.createUri((Class<Model>) anyObject(), anyLong())).thenReturn(null);
        onSetup();
    }

}