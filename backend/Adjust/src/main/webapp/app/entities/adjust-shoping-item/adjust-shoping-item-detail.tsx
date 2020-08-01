import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-shoping-item.reducer';
import { IAdjustShopingItem } from 'app/shared/model/adjust-shoping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustShopingItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustShopingItemDetail = (props: IAdjustShopingItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustShopingItemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustShopingItem.detail.title">AdjustShopingItem</Translate> [
          <b>{adjustShopingItemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustShopingItem.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustShopingItemEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.adjustShopingItem.description">Description</Translate>
            </span>
          </dt>
          <dd>{adjustShopingItemEntity.description}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.adjustShopingItem.token">Token</Translate>
            </span>
          </dt>
          <dd>{adjustShopingItemEntity.token}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.adjustShopingItem.price">Price</Translate>
            </span>
          </dt>
          <dd>{adjustShopingItemEntity.price}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.adjustShopingItem.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {adjustShopingItemEntity.image ? (
              <div>
                {adjustShopingItemEntity.imageContentType ? (
                  <a onClick={openFile(adjustShopingItemEntity.imageContentType, adjustShopingItemEntity.image)}>
                    <img
                      src={`data:${adjustShopingItemEntity.imageContentType};base64,${adjustShopingItemEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustShopingItemEntity.imageContentType}, {byteSize(adjustShopingItemEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="orderable">
              <Translate contentKey="adjustApp.adjustShopingItem.orderable">Orderable</Translate>
            </span>
          </dt>
          <dd>{adjustShopingItemEntity.orderable ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/adjust-shoping-item" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-shoping-item/${adjustShopingItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustShopingItem }: IRootState) => ({
  adjustShopingItemEntity: adjustShopingItem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustShopingItemDetail);
