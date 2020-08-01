import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './shoping-item.reducer';
import { IShopingItem } from 'app/shared/model/shoping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShopingItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ShopingItem = (props: IShopingItemProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { shopingItemList, match, loading } = props;
  return (
    <div>
      <h2 id="shoping-item-heading">
        <Translate contentKey="adjustApp.shopingItem.home.title">Shoping Items</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.shopingItem.home.createLabel">Create new Shoping Item</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {shopingItemList && shopingItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.image">Image</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.orderable">Orderable</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shopingItem.cart">Cart</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {shopingItemList.map((shopingItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${shopingItem.id}`} color="link" size="sm">
                      {shopingItem.id}
                    </Button>
                  </td>
                  <td>{shopingItem.name}</td>
                  <td>{shopingItem.description}</td>
                  <td>{shopingItem.token}</td>
                  <td>{shopingItem.price}</td>
                  <td>
                    {shopingItem.image ? (
                      <div>
                        {shopingItem.imageContentType ? (
                          <a onClick={openFile(shopingItem.imageContentType, shopingItem.image)}>
                            <img src={`data:${shopingItem.imageContentType};base64,${shopingItem.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {shopingItem.imageContentType}, {byteSize(shopingItem.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{shopingItem.orderable ? 'true' : 'false'}</td>
                  <td>{shopingItem.cartId ? <Link to={`cart/${shopingItem.cartId}`}>{shopingItem.cartId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${shopingItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${shopingItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${shopingItem.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.shopingItem.home.notFound">No Shoping Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ shopingItem }: IRootState) => ({
  shopingItemList: shopingItem.entities,
  loading: shopingItem.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShopingItem);
