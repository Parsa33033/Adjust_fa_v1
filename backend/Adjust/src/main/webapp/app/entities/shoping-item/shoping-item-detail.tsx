import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './shoping-item.reducer';
import { IShopingItem } from 'app/shared/model/shoping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShopingItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShopingItemDetail = (props: IShopingItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { shopingItemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.shopingItem.detail.title">ShopingItem</Translate> [<b>{shopingItemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.shopingItem.name">Name</Translate>
            </span>
          </dt>
          <dd>{shopingItemEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.shopingItem.description">Description</Translate>
            </span>
          </dt>
          <dd>{shopingItemEntity.description}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.shopingItem.token">Token</Translate>
            </span>
          </dt>
          <dd>{shopingItemEntity.token}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.shopingItem.price">Price</Translate>
            </span>
          </dt>
          <dd>{shopingItemEntity.price}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.shopingItem.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {shopingItemEntity.image ? (
              <div>
                {shopingItemEntity.imageContentType ? (
                  <a onClick={openFile(shopingItemEntity.imageContentType, shopingItemEntity.image)}>
                    <img
                      src={`data:${shopingItemEntity.imageContentType};base64,${shopingItemEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {shopingItemEntity.imageContentType}, {byteSize(shopingItemEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="orderable">
              <Translate contentKey="adjustApp.shopingItem.orderable">Orderable</Translate>
            </span>
          </dt>
          <dd>{shopingItemEntity.orderable ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="adjustApp.shopingItem.cart">Cart</Translate>
          </dt>
          <dd>{shopingItemEntity.cartId ? shopingItemEntity.cartId : ''}</dd>
        </dl>
        <Button tag={Link} to="/shoping-item" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/shoping-item/${shopingItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ shopingItem }: IRootState) => ({
  shopingItemEntity: shopingItem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShopingItemDetail);
