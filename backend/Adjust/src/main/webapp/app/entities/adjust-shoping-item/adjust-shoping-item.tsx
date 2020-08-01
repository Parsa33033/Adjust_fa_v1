import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-shoping-item.reducer';
import { IAdjustShopingItem } from 'app/shared/model/adjust-shoping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustShopingItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustShopingItem = (props: IAdjustShopingItemProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustShopingItemList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-shoping-item-heading">
        <Translate contentKey="adjustApp.adjustShopingItem.home.title">Adjust Shoping Items</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustShopingItem.home.createLabel">Create new Adjust Shoping Item</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustShopingItemList && adjustShopingItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.image">Image</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustShopingItem.orderable">Orderable</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustShopingItemList.map((adjustShopingItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustShopingItem.id}`} color="link" size="sm">
                      {adjustShopingItem.id}
                    </Button>
                  </td>
                  <td>{adjustShopingItem.name}</td>
                  <td>{adjustShopingItem.description}</td>
                  <td>{adjustShopingItem.token}</td>
                  <td>{adjustShopingItem.price}</td>
                  <td>
                    {adjustShopingItem.image ? (
                      <div>
                        {adjustShopingItem.imageContentType ? (
                          <a onClick={openFile(adjustShopingItem.imageContentType, adjustShopingItem.image)}>
                            <img
                              src={`data:${adjustShopingItem.imageContentType};base64,${adjustShopingItem.image}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustShopingItem.imageContentType}, {byteSize(adjustShopingItem.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{adjustShopingItem.orderable ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustShopingItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustShopingItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustShopingItem.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.adjustShopingItem.home.notFound">No Adjust Shoping Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustShopingItem }: IRootState) => ({
  adjustShopingItemList: adjustShopingItem.entities,
  loading: adjustShopingItem.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustShopingItem);
