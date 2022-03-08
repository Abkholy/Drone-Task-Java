

package com.musala.dronetask.generic;


import com.musala.dronetask.exceptions.CouldNotDeleteRecordException;
import com.musala.dronetask.exceptions.CouldNotUpdateRecordException;
import com.musala.dronetask.exceptions.NoRecordFoundException;
import com.musala.dronetask.ui.sys.RequestOperation;
import com.musala.dronetask.ui.sys.ResponseModel;
import com.musala.dronetask.ui.sys.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * Abstract base class for services that has operations for creating, reading,
 * updating and deleting entities.
 * This implementation uses RxJava.
 *
 * @param <E> Entity type.
 * @author Abdelrahman alkholy
 */

@Service
public abstract class AbstractService<E extends OEntity> {
    /* Constant(s): */

    /* Instance variable(s): */
    protected JpaRepository<E,Long> mRepository;

    /**
     * Creates a service instance that will use the supplied repository for entity persistence.
     *
     * @param inRepository Entity repository.
     */
    public AbstractService(final JpaRepository<E,Long> inRepository) {
        mRepository = inRepository;
    }


    @Autowired
    public EntityManager entityManager;
    /**
     * Saves the supplied entity.
     *
     * @param inEntity Entity to save.
     * @return Observable that will receive the saved entity, or exception if error occurs.
     * @p
     */
    public E save(final E inEntity)   {
             mRepository.save(inEntity);
             return mRepository.getById(inEntity.getId());
    }

    /**
     * @param inEntity entites to save
     * @return list of saved entites
     */
    public List<E> saveArray(final List<E> inEntity) {
        return mRepository.saveAll(inEntity);
    }

    /**
     * Updates the supplied entity.
     *
     * @param inEntity Entity to update.
     * @return Observable that will receive the updated entity, or exception if error occurs.
     */
    @Transactional(readOnly = false)
    public E update(final E inEntity) {
        try {
            return mRepository.saveAndFlush(inEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CouldNotUpdateRecordException(e.getMessage());
        }


    }

    /**
     * @param inEntity entites to update
     * @return array of updated entites
     */
    public List<E> update(final List<E> inEntity) {

        return mRepository.saveAll(inEntity);

    }

    /**
     * Finds the entity having supplied id.
     *
     * @param inEntityId Id of entity to retrieve.
     * @return Observable that will receive the found entity, or exception if
     * error occurs or no entity is found.
     */
    public E find(Long inEntityId) {
        return mRepository.findById(inEntityId).orElseThrow(NoRecordFoundException::new);
    }


    /** find all records paginated
     * @param page current page
     * @param pageSize page size
     * @return page object contains paginated records
     */
    public Page<E> findAll (int page, int pageSize ) {
        return mRepository.findAll(PageRequest.of(page, pageSize));

    }

    /**
     * @return list of all records
     */
    public List<E> findAll () {
        return mRepository.findAll();
    }

    /**
     * Deletes the entity having supplied id.
     *
     * @param inId Id of entity to delete.
     * @return Observable that will receive completion, or exception if error occurs.
     */
    public ResponseModel delete (final Long inId ) {
        ResponseModel responseModel = null;
        try {
            mRepository.deleteById(inId);
            responseModel = new ResponseModel(inId, RequestOperation.DELETE, ResponseStatus.SUCCESS);
        } catch (final Exception e) {
            e.printStackTrace();
            throw new CouldNotDeleteRecordException(e.getMessage());
        }
        return responseModel;
    }



}
