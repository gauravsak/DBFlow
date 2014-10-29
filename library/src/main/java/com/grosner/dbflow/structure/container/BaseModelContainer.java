package com.grosner.dbflow.structure.container;

import com.grosner.dbflow.config.FlowManager;
import com.grosner.dbflow.sql.SqlUtils;
import com.grosner.dbflow.structure.Model;
import com.grosner.dbflow.structure.ModelAdapter;

/**
 * Author: andrewgrosner
 * Contributors: { }
 * Description:
 */
public abstract class BaseModelContainer<ModelClass extends Model, DataClass> implements ModelContainer<ModelClass, DataClass>, Model {

    /**
     * The {@link ModelClass} that the json corresponds to. Use {@link #toModel()} to retrieve this value.
     */
    ModelClass mModel;

    /**
     * The {@link com.grosner.dbflow.structure.TableStructure} that is defined for this {@link org.json.JSONObject}
     */
    ModelAdapter<ModelClass> mModelAdapter;

    ContainerAdapter<ModelClass> mContainerAdapter;

    /**
     * The data thats stored in the container
     */
    DataClass mData;

    public BaseModelContainer(Class<ModelClass> table) {
        mModelAdapter = FlowManager.getModelAdapter(table);
    }

    public BaseModelContainer(Class<ModelClass> table, DataClass data) {
        mModelAdapter = FlowManager.getModelAdapter(table);
        mContainerAdapter = FlowManager.getContainerAdapter(table);
        mData = data;
    }

    @Override
    public ModelClass toModel() {
        if (mModel == null) {
            mModel = mContainerAdapter.toModel(this);
        }

        return mModel;
    }

    @Override
    public DataClass getData() {
        return mData;
    }

    public void setData(DataClass data) {
        mData = data;
    }

    @Override
    public abstract Object getValue(String columnName);

    @Override
    public abstract void put(String columnName, Object value);

    @Override
    public ModelAdapter<ModelClass> getModelAdapter() {
        return mModelAdapter;
    }

    @Override
    public Class<ModelClass> getTable() {
        return mModelAdapter.getModelClass();
    }

    @Override
    public void save(boolean async) {
        mContainerAdapter.save(async, this, SqlUtils.SAVE_MODE_DEFAULT);
    }

    @Override
    public void delete(boolean async) {
        mContainerAdapter.delete(async, this);
    }

    @Override
    public void update(boolean async) {
        mContainerAdapter.save(async, this, SqlUtils.SAVE_MODE_UPDATE);
    }

    @Override
    public void insert(boolean async) {
        mContainerAdapter.save(async, this, SqlUtils.SAVE_MODE_INSERT);
    }

    @Override
    public boolean exists() {
        return mContainerAdapter.exists(this);
    }
}
