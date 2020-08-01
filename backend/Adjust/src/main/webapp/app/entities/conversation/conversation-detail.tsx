import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './conversation.reducer';
import { IConversation } from 'app/shared/model/conversation.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IConversationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConversationDetail = (props: IConversationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { conversationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.conversation.detail.title">Conversation</Translate> [<b>{conversationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <Translate contentKey="adjustApp.conversation.client">Client</Translate>
          </dt>
          <dd>{conversationEntity.clientId ? conversationEntity.clientId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.conversation.specialist">Specialist</Translate>
          </dt>
          <dd>{conversationEntity.specialistId ? conversationEntity.specialistId : ''}</dd>
        </dl>
        <Button tag={Link} to="/conversation" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/conversation/${conversationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ conversation }: IRootState) => ({
  conversationEntity: conversation.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConversationDetail);
